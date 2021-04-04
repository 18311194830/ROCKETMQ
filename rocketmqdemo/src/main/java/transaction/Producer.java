package transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SyncProducer
 * @Description: 发送同步消息
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 14:53$
 * @Version: 1.0
 */

public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        TransactionMQProducer producer = new TransactionMQProducer("group5");
        //2.指定NameServer地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //生产者事务监听器
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 在该方法中执行本地事务
             * @param message
             * @param o
             * @return
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if (StringUtils.equals("TagA", message.getTags())) {
                    //提交
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (StringUtils.equals("TagB", message.getTags())) {
                    //回滚
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else {
                    //不做处理
                    return LocalTransactionState.UNKNOW;
                }
            }

            /**
             * 该方法是MQ进行消失事务状态的回查
             * @param messageExt
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("回查消息状态的Tag" + messageExt.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        //3.启动producer创建消息对象，指定主题Topic，Tag和消息体
        producer.start();

        String[] tag = {"TagA", "TagB", "TagC"};
        for (int i = 0; i < 3; i++) {
            //参数一 消息主题：Topic
            //参数二 消息类别：Tag
            //参数三 消息内容
            Message message = new Message("TransactionTopic", tag[i], ("Hello Word" + i).getBytes());
            //4.发送消息(null:对所有消息进行事务控制)
            SendResult sendResult = producer.sendMessageInTransaction(message, null);
            //消息发送状态
            SendStatus status = sendResult.getSendStatus();
            //消息Id
            String msgId = sendResult.getMsgId();
            //消息接收队列Id
            int queueId = sendResult.getMessageQueue().getQueueId();
            System.out.println("消息发送结果:" + sendResult);
            //消息停一秒
            TimeUnit.SECONDS.sleep(1);
        }

        //5.关闭生产者producer
        //producer.shutdown();
    }
}

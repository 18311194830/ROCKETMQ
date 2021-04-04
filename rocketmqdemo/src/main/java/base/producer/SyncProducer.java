package base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SyncProducer
 * @Description: 发送同步消息
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 14:53$
 * @Version: 1.0
 */

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定NameServer地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3.启动producer创建消息对象，指定主题Topic，Tag和消息体
        producer.start();
        for (int i = 0; i < 10; i++) {
            //参数一 消息主题：Topic
            //参数二 消息类别：Tag
            //参数三 消息内容
            Message message = new Message("base", "Tag1", ("Hello Word" + i).getBytes());
            //4.发送消息
            SendResult sendResult = producer.send(message);
            //消息发送状态
            SendStatus status = sendResult.getSendStatus();
            //消息Id
            String msgId = sendResult.getMsgId();
            //消息接收队列Id
            int queueId = sendResult.getMessageQueue().getQueueId();
            System.out.println("消息发送状态:"+status+",消息Id"+msgId+",消息接收队列Id"+queueId);
            //消息停一秒
            TimeUnit.SECONDS.sleep(1);
        }

        //5.关闭生产者producer
        producer.shutdown();
    }
}

package filter.tag;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @ClassName: Consumer
 * @Description: Tag过滤
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 17:18$
 * @Version: 1.0
 */

public class Consumer {
    public static void main(String[] args) throws Exception {
        //1. 创建消息消费者consumer，并制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2. 指定NameServer地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //3. 订阅主题Topic，Tag
        consumer.subscribe("filterTopic", "Tag1 || Tag2");
        //设定消费模式（默认为负载均衡）
        //4. 设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            /**
             * 接收消息内容
             * @param list
             * @param consumeConcurrentlyContext
             * @return
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list) {
                    System.out.println("消息Id：[" + msg.getMsgId() + "],消息延迟时间：" + (System.currentTimeMillis() - msg.getStoreTimestamp()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5. 启动消费者consumer
        consumer.start();
    }
}

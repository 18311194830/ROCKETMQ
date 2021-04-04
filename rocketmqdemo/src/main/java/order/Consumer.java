package order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @ClassName: Consumer
 * @Description: java类作用描述
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 16:58$
 * @Version: 1.0
 */

public class Consumer {
    public static void main(String[] args) throws Exception {
        //1. 创建消息消费者consumer，并制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("groupOrder");
        //2. 指定NameServer地址
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //3. 订阅主题Topic，Tag
        consumer.subscribe("OrderTopic", "*");
        //4. 设置回调函数，处理消息(注册监听器)
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    System.out.println("线程名称:[" + Thread.currentThread().getName() + "],消费消息：" + new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        //5. 启动消费者consumer
        consumer.start();
        System.out.println("消费消息启动");
    }
}

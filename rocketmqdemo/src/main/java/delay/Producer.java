package delay;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @ClassName: Producer
 * @Description: 延时消息生产者
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 17:18$
 * @Version: 1.0
 */

public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("delay", "DelayTag", ("Hello world" + i).getBytes());
            //设定延迟时间
            message.setDelayTimeLevel(1);
            SendResult send = producer.send(message);
            System.out.println("消息发送结果：" + send);
        }
        producer.shutdown();
    }
}

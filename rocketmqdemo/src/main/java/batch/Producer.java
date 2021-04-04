package batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

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

        List<Message> list = new ArrayList<>();
        Message message1 = new Message("batch", "BatchTag", ("Hello world" + 1).getBytes());
        Message message2 = new Message("batch", "BatchTag", ("Hello world" + 2).getBytes());
        Message message3 = new Message("batch", "BatchTag", ("Hello world" + 3).getBytes());

        list.add(message1);
        list.add(message2);
        list.add(message3);

        SendResult send = producer.send(list);
        System.out.println("消息发送结果：" + send);

        producer.shutdown();
    }
}

package order;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @ClassName: Producer
 * @Description: java类作用描述
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 16:36$
 * @Version: 1.0
 */

public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("groupOrder");
        //2.指定NameServer地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3.启动producer创建消息对象，指定主题Topic，Tag和消息体
        producer.start();
        //构建消息集合
        List<OrderStep> orderStepList = OrderStep.buildOrders();
        //发送消息
        for (int i = 0; i < orderStepList.size(); i++) {
            String body = orderStepList.get(i) + "";
            Message message = new Message("OrderTopic", "order", "i" + i, body.getBytes());
            /**
             * 参数一 消息对象
             * 参数二 消息队列的选择器
             * 参数三 选择队列的业务标识
             */
            SendResult send = producer.send(message, new MessageQueueSelector() {
                /**
                 *
                 * @param list    消息队列的集合
                 * @param message 消息对象
                 * @param object  业务标识的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object object) {
                    long orderId = (long) object;
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, orderStepList.get(i).getOrderId());

            System.out.println("发送结果：" + send);
        }
    }
}

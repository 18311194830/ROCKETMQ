package order;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OrderStep
 * @Description: 订单构建
 * @Author: 任宏腾
 * @CreateDate: 2020/9/12$ 16:28$
 * @Version: 1.0
 */

public class OrderStep {
    private long orderId;
    private String desc;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static List<OrderStep> buildOrders(){
        List<OrderStep> list = new ArrayList<>();

        //1039  创建，付款，推送，完成
        //1065  创建，付款，
        //7235  创建，付款，
        OrderStep orderDemo = new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("创建");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(1065L);
        orderDemo.setDesc("创建");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(7235L);
        orderDemo.setDesc("创建");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("付款");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(1065L);
        orderDemo.setDesc("付款");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(7235L);
        orderDemo.setDesc("付款");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("推送");
        list.add(orderDemo);

        orderDemo = new OrderStep();
        orderDemo.setOrderId(1039L);
        orderDemo.setDesc("完成");
        list.add(orderDemo);

        return list;
    }
}

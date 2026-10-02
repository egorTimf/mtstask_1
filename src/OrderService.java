import Delivery.DeliveryMethod;
import OrderClasses.Order;
import OrderClasses.OrderProducts;
import OrderClasses.OrderStatus;
import People.Courier;
import People.Customer;

import java.util.ArrayList;

public class OrderService {
    private ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void createOrder(Customer customer, DeliveryMethod deliveryMethod, String address) {
        Order newOrd = new Order(customer, deliveryMethod, address, orderList.size()+ 1);
        orderList.add(newOrd);
    }

    public void setDeliveryMethod(Order order, DeliveryMethod deliveryMethod){
        order.setDeliveryMethod(deliveryMethod);
    }

    public Order getOrder(Integer index) {
        return orderList.get(index);
    }

    public void setStatusForOrder(Order order, OrderStatus status) {
        order.setStatus(status);
    }
}

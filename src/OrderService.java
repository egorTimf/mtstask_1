import Delivery.DeliveryMethod;
import OrderClasses.Order;
import OrderClasses.OrderProducts;
import People.Customer;

import java.util.ArrayList;

public class OrderService {
    private ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void CreateOrder(Customer cust, DeliveryMethod deliveryMethod, String address, OrderProducts products) {
        Order newOrd = new Order(cust, products, deliveryMethod, address, orderList.size()+ 1);
        orderList.add(newOrd);
    }

    public void SetDeliveryMethod(Order order, DeliveryMethod deliveryMethod){
        order.setDeliveryMethod(deliveryMethod);
    }

    public Order GetOrder(Integer index) {
        return orderList.get(index);
    }
}

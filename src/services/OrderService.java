package services;

import Delivery.DeliveryMethod;
import OrderClasses.Order;
import OrderClasses.OrderStatus;
import People.Customer;

import java.util.ArrayList;

public class OrderService {
    private ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public Order createOrder(Customer customer, String address) {
        Order newOrd = new Order(customer, address,orderList.size()+ 1);
        orderList.add(newOrd);
        return newOrd;
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

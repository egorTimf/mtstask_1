package OrderClasses;

import Delivery.DeliveryMethod;
import People.Courier;
import People.Customer;

public class Order {
    private Customer customer;
    private Courier courier;
    private OrderProducts products;
    private String address;
    private OrderStatus status;
    private Integer price;
    private DeliveryMethod delivery;
    private Integer number;

    public Order(Customer customer, DeliveryMethod delivery, String address, Integer number) {
        this.customer = customer;
        this.products = new OrderProducts();
        this.address = address;
        this.status = OrderStatus.NEW;
        this.delivery = delivery;
        this.number = number;
        this.calculatePrice();
    }

    public void addItem(Product product, Integer count) throws Exception {
        products.addItem(product, count);
        calculatePrice();
    }

    public void removeItem(Product product, Integer count) throws Exception {
        products.removeItem(product, count);
        calculatePrice();
    }

    private void calculatePrice() {
        price = products.calculatePrice() + delivery.calculatePrice(address, customer.getAddress());
    }

    public Integer getPrice() { return price; }

    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus st) { status = st; }

    public Courier getCourier() { return courier; }

    public void setCourier(Courier cour) { courier = cour; }

    public Integer getTime() { return delivery.calculateTime(address, customer.getAddress()); }

    public Integer getDeliveryPrice() { return delivery.calculatePrice(address, customer.getAddress()); }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        delivery = deliveryMethod;
    }
}

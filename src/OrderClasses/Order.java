package OrderClasses;

import Delivery.DeliveryMethod;
import Delivery.StandardDelivery;
import People.Courier;
import People.Customer;

public class Order {
    private Customer customer;
    private Courier courier;
    private OrderItems products;
    private String address;
    private OrderStatus status;
    private int price;
    private DeliveryMethod delivery;
    private int number;

    public Order(Customer customer, String address, int number) {
        this.customer = customer;
        this.products = new OrderItems();
        this.address = address;
        this.status = OrderStatus.NEW;
        this.number = number;
        this.delivery = new StandardDelivery();
        this.calculatePrice();
    }

    public void addItem(Product product, int count) throws Exception {
        products.addItem(product, count);
        calculatePrice();
    }

    public void removeItem(Product product, int count) throws Exception {
        products.removeItem(product, count);
        calculatePrice();
    }

    private void calculatePrice() {
        price = products.calculatePrice() + delivery.calculatePrice(address);
    }

    public String getAddress() { return address; }

    public int getPrice() { return price; }

    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus st) { status = st; }

    public Courier getCourier() { return courier; }

    public void setCourier(Courier cour) { courier = cour; }

    public int getTime() { return delivery.calculateTime(address); }

    public int getDeliveryPrice() { return delivery.calculatePrice(address); }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        delivery = deliveryMethod;
    }
    
    public int getNumber() { return number; }

    public DeliveryMethod getDeliveryMethod() {
        return delivery;
    }

    public String getShortInfo() {
        return String.format("Заказ №%d | %s | статус: %s | сумма: %d руб.%s",
                number,
                customer != null ? customer.toString() : "без клиента",
                status,
                price,
                courier != null ? " | курьер: " + courier : "");
    }

    public void changeStatus(OrderStatus next) {
        if (next == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }
        if (!status.canTransitionTo(next)) {
            throw new IllegalStateException(
                    "Нельзя перейти из " + status + " в " + next);
        }
        status = next;

        if ((next == OrderStatus.DELIVERED || next == OrderStatus.CANCELLED)
                && courier != null) {
            courier.setStatus(People.CourierStatus.free);
            courier = null;
        }
    }

    public java.util.List<OrderStatus> getAvailableNextStatuses() {
        return status.getNextStatuses();
    }
}

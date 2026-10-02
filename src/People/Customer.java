package People;

import OrderClasses.Order;

public class Customer extends People {
    private String address;
    private Order order;

    public Order getOrder() { return order; }

    public void setOrder(Order order) {  this.order = order; }

    public String getAddress() { return address; }
    public void setAddress(String adr) {  address = adr; }

    public Customer(String name, String surname, String address) {
        super(name, surname);
        this.address = address;
    }


}

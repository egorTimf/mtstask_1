package People;

import OrderClasses.Order;

public class Customer extends People {
    private String _address;
    private Order _order;

    public Order GetOrder() { return _order; }

    public void SetOrder(Order order) {  _order = order; }

    public String GetAddress() { return _address; }
    public void SetAddress(String adr) {  _address = adr; }

    public Customer(String name, String surname, String address) {
        super(name, surname);
        _address = address;
    }


}

package OrderClasses;

import Delivery.DeliveryMethod;
import People.Courier;
import People.Customer;

public class Order {
    private Customer _customer;
    private Courier _courier;
    private OrderProducts _products;
    private String _address;
    private OrderStatus _status;
    private Integer _price;
    private DeliveryMethod _delivery;
    private Integer _number;

    public Order(Customer customer, OrderProducts products, DeliveryMethod delivery, String address, Integer number) {
        _customer = customer;
        _products = products;
        _address = address;
        _status = OrderStatus.NEW;
        _delivery = delivery;
        _number = number;
        CalculatePrice();
    }

    public void AddItem(Product product, Integer count) throws Exception {
        _products.AddItem(product, count);
        CalculatePrice();
    }

    public void RemoveItem(Product product, Integer count) throws Exception {
        _products.RemoveItem(product, count);
        CalculatePrice();
    }

    private void CalculatePrice() {
        _price = _products.CalculatePrice() + _delivery.CalculatePrice(_address, _customer.GetAddress());
    }

    public Integer GetPrice() { return _price; }

    public OrderStatus GetStatus() { return _status; }
    public void SetStatus(OrderStatus st) { _status = st; }

    public Courier GetCourier() { return _courier; }
    public void SetCourier(Courier cour) { _courier = cour; }

    public Integer GetTime() { return _delivery.CalculateTime(_address, _customer.GetAddress()); }
    public Integer GetDeliveryPrice() { return _delivery.CalculatePrice(_address, _customer.GetAddress()); }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        _delivery = deliveryMethod;
    }

}

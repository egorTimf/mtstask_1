public class Order {
    private Customer _customer;
    private Courier _courier;
    private OrderProducts _products;
    private String _address;
    private OrderStatus _status;
    private DeliveryMethod _delivery;
    private Integer _price;


    public void Order(Customer customer, OrderProducts products, DeliveryMethod delivery) {
        _courier = delivery.;
        _customer = customer;
        _products = products;
        _address = customer.GetAddress();
        _status = OrderStatus.NEW;
        _price = products.CalculatePrice();
        _delivery = delivery;
    }

    public void AddItem(Product product, Integer count) throws Exception {
        _products.AddItem(product, count);
        _price = _products.CalculatePrice() + _delivery.getPrice();
    }

    public void RemoveItem(Product product, Integer count) throws Exception {
        _products.RemoveItem(product, count);
        _price = _products.CalculatePrice() + _delivery.getPrice();
    }

    public Integer GetPrice() { return _price + _delivery.getPrice(); }

    public OrderStatus GetStatus() { return _status; }
    public void SetStatus(OrderStatus st) { _status = st; }

    public Courier GetCourier() { return _courier; }
    public void SetCourier(Courier cour) { _courier = cour; }

    public Integer GetTime() { return _delivery.getTime(); }

}

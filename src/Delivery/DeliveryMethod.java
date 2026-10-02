package Delivery;

public interface DeliveryMethod {

    public Integer calculateTime(String address);
    public Integer calculatePrice(String address);
    boolean courierIsNeed();
}
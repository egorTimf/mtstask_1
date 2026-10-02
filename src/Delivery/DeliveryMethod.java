package Delivery;

public interface DeliveryMethod {

    public Integer calculateTime(String address1, String address2);
    public Integer calculatePrice(String address1, String address2);
    boolean courierIsNeed();
}
package Delivery;

public interface DeliveryMethod {

    public Integer CalculateTime(String Address1, String Address2);
    public Integer CalculatePrice(String Address1, String Address2);
    boolean CourierIsNeed();
}
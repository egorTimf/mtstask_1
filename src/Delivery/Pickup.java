package Delivery;

public class Pickup implements DeliveryMethod {
    @Override
    public Integer calculateTime(String address1, String address2) {
        return 0;
    }

    @Override
    public Integer calculatePrice(String address1, String address2) {
        return 0;
    }

    @Override
    public boolean courierIsNeed() {
        return false;
    }
}

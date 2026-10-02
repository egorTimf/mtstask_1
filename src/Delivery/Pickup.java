package Delivery;

public class Pickup implements DeliveryMethod {
    @Override
    public Integer calculateTime(String address) {
        return 0;
    }

    @Override
    public Integer calculatePrice(String address) {
        return 0;
    }

    @Override
    public boolean courierIsNeed() {
        return false;
    }
}

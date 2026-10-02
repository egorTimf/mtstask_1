package Delivery;

public class StandardDelivery implements DeliveryMethod {
    @Override
    public Integer calculatePrice(String address) {
        return 100;
    }

    @Override
    public Integer calculateTime(String address) {
        return 10;
    }

    @Override
    public boolean courierIsNeed() {
        return true;
    }
}

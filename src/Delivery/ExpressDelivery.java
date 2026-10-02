package Delivery;

public class ExpressDelivery implements DeliveryMethod {
    @Override
    public Integer calculatePrice(String address) {
        return 1000;
    }

    @Override
    public Integer calculateTime(String address) {
        return 5;
    }

    @Override
    public boolean courierIsNeed() {
        return true;
    }
}
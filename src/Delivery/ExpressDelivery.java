package Delivery;

public class ExpressDelivery implements DeliveryMethod {
    @Override
    public Integer calculatePrice(String address1, String address2) {
        return 1000;
    }

    @Override
    public Integer calculateTime(String address1, String address2) {
        return 5;
    }

    @Override
    public boolean courierIsNeed() {
        return true;
    }
}
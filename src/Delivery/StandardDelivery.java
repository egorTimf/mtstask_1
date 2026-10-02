package Delivery;

public class StandardDelivery implements DeliveryMethod {
    @Override
    public Integer calculatePrice(String address1, String address2) {
        return 100;
    }

    @Override
    public Integer calculateTime(String address1, String address2) {
        return 10;
    }

    @Override
    public boolean courierIsNeed() {
        return true;
    }
}

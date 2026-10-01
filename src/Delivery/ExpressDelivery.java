package Delivery;

public class ExpressDelivery implements DeliveryMethod {
    @Override
    public Integer CalculatePrice(String Address1, String Address2) {
        return 1000;
    }

    @Override
    public Integer CalculateTime(String Address1, String Address2) {
        return 5;
    }

    @Override
    public boolean CourierIsNeed() {
        return true;
    }
}
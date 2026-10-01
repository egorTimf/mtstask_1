package Delivery;

public class StandardDelivery implements DeliveryMethod {
    @Override
    public Integer CalculatePrice(String Address1, String Address2) {
        return 100;
    }

    @Override
    public Integer CalculateTime(String Address1, String Address2) {
        return 10;
    }

    @Override
    public boolean CourierIsNeed() {
        return true;
    }
}

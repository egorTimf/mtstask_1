package Delivery;

public class Pickup implements DeliveryMethod {
    @Override
    public Integer CalculateTime(String Address1, String Address2) {
        return 0;
    }

    @Override
    public Integer CalculatePrice(String Address1, String Address2) {
        return 0;
    }

    @Override
    public boolean CourierIsNeed() {
        return false;
    }
}

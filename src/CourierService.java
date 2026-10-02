import OrderClasses.Order;
import People.Courier;
import People.CourierStatus;

import java.util.ArrayList;

public class CourierService {
    private ArrayList<Courier> couriers = new ArrayList<>();

    public ArrayList<Courier> getCouriers() {
        return couriers;
    }

    public Courier getCourier(Integer index){
        return couriers.get(index);
    }

    public void addCourier(Courier courier) {
        couriers.add(courier);
    }

    public void setCourierForOrder(Courier courier, Order order){
        if(order.getCourier() != null)
            order.getCourier().setStatus(CourierStatus.free);

        order.setCourier(courier);
        courier.setStatus(CourierStatus.busy);
    }
}

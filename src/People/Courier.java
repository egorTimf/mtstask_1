package People;

public class Courier extends People {
    private CourierStatus status;

    public Courier(String name, String surname) {
        super(name, surname);
        status = CourierStatus.free;
    }

    public CourierStatus getStatus() {
        return status;
    }

    public void setStatus(CourierStatus status) {
        this.status = status;
    }
}

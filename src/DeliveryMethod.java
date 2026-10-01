public class DeliveryMethod {
    private Integer _price;
    private Integer _time;
    private Courier _courier;

    public Integer getPrice() {
        return _price;
    }

    public void setPrice(Integer price) {
        this._price = price;
    }

    public Integer getTime() {
        return _time;
    }

    public void setTime(Integer time) {
        this._time = time;
    }
}
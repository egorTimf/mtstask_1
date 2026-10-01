package OrderClasses;

import java.util.Objects;

public class Product {
    private String _name;
    private Integer _price;
    public String GetName() { return _name; }

    public void SetName(String name) { _name = name; }

    public Integer GetPrice() {return _price ; }
    public void SetPrice(Integer price) throws Exception {
        if(price < 0) throw new Exception("Negative Price");
        _price = price;
    }

    public Product(String name, Integer price) {
        _price = price;
        _name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name);
    }
}

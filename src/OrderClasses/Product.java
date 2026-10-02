package OrderClasses;

import java.util.Objects;

public class Product {
    private String name;
    private Integer price;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getPrice() {return price ; }

    public void setPrice(Integer price) throws Exception {
        if(price < 0) throw new Exception("Negative Price");
        this.price = price;
    }

    public Product(String name, Integer price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

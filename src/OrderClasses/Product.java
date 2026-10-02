package OrderClasses;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть < 0");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return name.equals(((Product) o).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " (" + price + " руб.)";
    }
}

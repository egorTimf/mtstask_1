import OrderClasses.Product;
import OrderClasses.ProductCatalog;
import People.Courier;
import People.CourierStatus;

public class Main {
    public static void main(String[] args) {
        // --- Каталог товаров ---
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.addProduct(new Product("Хлеб", 50));
        productCatalog.addProduct(new Product("Молоко", 90));
        productCatalog.addProduct(new Product("Сыр", 300));
        productCatalog.addProduct(new Product("Колбаса", 250));
        productCatalog.addProduct(new Product("Яблоки", 120));

        // --- Сервисы ---
        OrderService orderService = new OrderService();
        CourierService courierService = new CourierService();

        // --- Курьеры ---
        courierService.addCourier(new Courier("Иван", "Петров"));
        courierService.addCourier(new Courier("Сергей", "Сидоров"));
        courierService.addCourier(new Courier("Анна", "Смирнова"));

        // --- Консольный интерфейс ---
        ConsoleUI ui = new ConsoleUI(orderService, courierService, productCatalog);
        ui.run();
    }
}
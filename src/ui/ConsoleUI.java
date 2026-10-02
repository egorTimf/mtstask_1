package ui;

import Delivery.DeliveryMethod;
import Delivery.ExpressDelivery;
import Delivery.Pickup;
import Delivery.StandardDelivery;
import OrderClasses.Order;
import OrderClasses.OrderStatus;
import OrderClasses.Product;
import OrderClasses.ProductCatalog;
import People.Courier;
import People.Customer;
import services.CourierService;
import services.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final OrderService orderService;
    private final CourierService courierService;
    private final ProductCatalog productCatalog;
    private boolean running = true;

    public ConsoleUI(OrderService orderService,
                     CourierService courierService,
                     ProductCatalog productCatalog) {
        this.orderService = orderService;
        this.courierService = courierService;
        this.productCatalog = productCatalog;
    }

    public void run() {
        System.out.println("=== Симулятор службы доставки ===");
        while (running) {
            printMenu();
            int choice = readInt("Выберите пункт: ");
            try {
                handleChoice(choice);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка: " + e.getMessage());
            }
        }
        System.out.println("Работа завершена.");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Создать новый заказ");
        System.out.println("2. Показать список заказов");
        System.out.println("3. Выбрать способ доставки");
        System.out.println("4. Назначить курьера");
        System.out.println("5. Изменить статус заказа");
        System.out.println("6. Показать информацию о заказе");
        System.out.println("7. Показать каталог товаров");
        System.out.println("8. Добавить товар в каталог");
        System.out.println("0. Выход");
    }

    private void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1 -> createOrder();
            case 2 -> listOrders();
            case 3 -> chooseDeliveryMethod();
            case 4 -> assignCourier();
            case 5 -> changeStatus();
            case 6 -> showOrder();
            case 7 -> showCatalog();
            case 8 -> addProductToCatalog();
            case 0 -> running = false;
            default -> System.out.println("Неизвестный пункт меню.");
        }
    }

    private void createOrder() throws Exception {
        System.out.println("--- Новый заказ ---");
        String name = readNonBlankLine("Имя клиента: ", "Имя");
        String surname = readNonBlankLine("Фамилия клиента: ", "Фамилия");
        String address = readNonBlankLine("Адрес доставки: ", "Адрес");

        Customer customer = new Customer(name, surname, address);
        Order order = orderService.createOrder(customer, address);

        addItemsToOrder(order);

        System.out.println("Заказ создан: " + order.getShortInfo());
    }

    private void addItemsToOrder(Order order) throws Exception {
        while (true) {
            System.out.println();
            System.out.println("1. Выбрать товар из каталога");
            System.out.println("0. Закончить добавление");

            int choice = readInt("Ваш выбор: ");
            Product product;
            switch (choice) {
                case 1 -> product = selectProductFromCatalog();
                case 0 -> {
                    return;
                }
                default -> {
                    System.out.println("Неверный пункт.");
                    continue;
                }
            }

            if (product == null) {
                continue;
            }

            int quantity = readPositiveInt("Количество: ", "Количество");
            order.addItem(product, quantity);
            System.out.printf("Добавлено: %s x%d%n", product.getName(), quantity);
        }
    }

    private void listOrders() {
        List<Order> orders = orderService.getOrderList();
        if (orders.isEmpty()) {
            System.out.println("Заказов пока нет.");
            return;
        }
        System.out.println("--- Заказы ---");
        for (Order order : orders) {
            System.out.println(order.getShortInfo());
        }
    }

    private void chooseDeliveryMethod() {
        Order order = selectOrder();
        if (order == null) {
            return;
        }

        System.out.println("Выберите способ доставки:");
        System.out.println("1. Стандартная курьером");
        System.out.println("2. Экспресс");
        System.out.println("3. Самовывоз");
        int choice = readInt("Ваш выбор: ");

        DeliveryMethod method = switch (choice) {
            case 1 -> new StandardDelivery();
            case 2 -> new ExpressDelivery();
            case 3 -> new Pickup();
            default -> throw new IllegalArgumentException("Неверный способ доставки");
        };

        order.setDeliveryMethod(method);

        String address = order.getAddress();
        System.out.printf("Стоимость доставки: %d, срок: %d дн.%n",
                method.calculatePrice(address),
                method.calculateTime(address));
        System.out.printf("Итоговая стоимость заказа: %d%n", order.getPrice());
    }

    private void assignCourier() {
        Order order = selectOrder();
        if (order == null)
            return;

        if (order.getDeliveryMethod() == null) {
            System.out.println("Сначала выберите способ доставки.");
            return;
        }
        if (!order.getDeliveryMethod().courierIsNeed()) {
            System.out.println("Этот способ доставки не требует курьера.");
            return;
        }
        if (order.getCourier() != null) {
            System.out.println("Курьер уже назначен: " + order.getCourier());
            return;
        }

        ArrayList<Courier> free = courierService.getFreeCouriers();
        if (free.isEmpty()) {
            System.out.println("Свободных курьеров нет.");
            return;
        }

        System.out.println("Свободные курьеры:");
        for (int i = 0; i < free.size(); i++)
            System.out.printf("%d. %s%n", i + 1, free.get(i));


        int idx = readIntInRange("Выберите номер: ", 1, free.size(), "Номер курьера") - 1;

        Courier courier = free.get(idx);
        courierService.setCourierForOrder(courier, order);
        System.out.println("Курьер назначен: " + courier);
    }

    private void changeStatus() {
        Order order = selectOrder();
        if (order == null)
            return;

        System.out.println("Текущий статус: " + order.getStatus());
        List<OrderStatus> next = order.getAvailableNextStatuses();
        if (next.isEmpty()) {
            System.out.println("Нет доступных переходов.");
            return;
        }

        System.out.println("Доступные переходы:");
        for (int i = 0; i < next.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, next.get(i));
        }

        int idx = readIntInRange("Выберите: ", 1, next.size(), "Номер перехода") - 1;

        order.changeStatus(next.get(idx));
        System.out.println("Новый статус: " + order.getStatus());
    }

    private void showOrder() {
        Order order = selectOrder();
        if (order != null) {
            System.out.println(order.getShortInfo());
        }
    }

    private void showCatalog() {
        List<Product> products = productCatalog.getAll();
        if (products.isEmpty()) {
            System.out.println("Каталог пуст.");
            return;
        }
        System.out.println("--- Каталог товаров ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, products.get(i));
        }
    }

    private void addProductToCatalog() {
        Product product = createAndRegisterProduct();
        if (product != null) {
            System.out.println("Товар добавлен в каталог: " + product);
        }
    }

    private Product selectProductFromCatalog() {
        List<Product> products = productCatalog.getAll();
        if (products.isEmpty()) {
            System.out.println("Каталог пуст. Сначала добавьте товар.");
            return null;
        }

        System.out.println("Доступные товары (0 — отмена):");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, products.get(i));
        }

        while (true) {
            int idx = readInt("Выберите товар: ");
            if (idx == 0) {
                return null;
            }
            if (idx >= 1 && idx <= products.size()) {
                return products.get(idx - 1);
            }
            System.out.println("Неверный номер, попробуйте снова.");
        }
    }

    private Product createAndRegisterProduct() {
        String name = readLine("Название товара (пусто — отмена): ");
        if (name.isBlank()) {
            return null;
        }

        if (productCatalog.contains(name)) {
            System.out.println("Такой товар уже есть в каталоге");
            return productCatalog.findByName(name);
        }

        int price = readNonNegativeInt("Цена за единицу: ", "Цена");
        Product product = new Product(name, price);
        productCatalog.addProduct(product);
        return product;
    }

    private Order selectOrder() {
        List<Order> orders = orderService.getOrderList();
        if (orders.isEmpty()) {
            System.out.println("Заказов нет.");
            return null;
        }

        System.out.println("Выберите заказ:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, orders.get(i).getShortInfo());
        }

        int idx = readIntInRange("Номер: ", 1, orders.size(), "Номер заказа") - 1;
        return orders.get(idx);
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String readNonBlankLine(String prompt, String fieldName) {
        while (true) {
            String value = readLine(prompt);
            try {
                InputValidator.requireNonBlank(value, fieldName);
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return InputValidator.parseInt(line, "Значение");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readPositiveInt(String prompt, String fieldName) {
        while (true) {
            int value = readInt(prompt);
            try {
                InputValidator.requirePositive(value, fieldName);
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readNonNegativeInt(String prompt, String fieldName) {
        while (true) {
            int value = readInt(prompt);
            try {
                InputValidator.requireNonNegative(value, fieldName);
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int readIntInRange(String prompt, int min, int max, String fieldName) {
        while (true) {
            int value = readInt(prompt);
            try {
                InputValidator.requireInRange(value, min, max, fieldName);
                return value;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
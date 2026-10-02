package ui;

public final class InputValidator {

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " не может быть пустым");
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " должно быть > 0");
        }
    }

    public static void requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " не может быть < 0");
        }
    }

    public static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    fieldName + " должно быть от " + min + " до " + max);
        }
    }

    public static void requirePhone(String phone) {
        requireNonBlank(phone, "Телефон");
        String digits = phone.replaceAll("[^0-9]", "");
        if (digits.length() < 10 || digits.length() > 15) {
            throw new IllegalArgumentException("Телефон должен содержать 10–15 цифр");
        }
    }

    public static int parseInt(String raw, String fieldName) {
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " должно быть целым числом");
        }
    }
}
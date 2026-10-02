package People;

public class People {
    private final String name;
    private final String surname;

    public String getName() { return name; }
    public String getSurname() { return surname; }

    public People(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

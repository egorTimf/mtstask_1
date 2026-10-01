package People;

public class People {
    private final String Name;
    private final String Surname;

    public String GetName() { return Name; }
    public String GetSurname() { return Surname; }

    public People(String name, String surname) {
        Name = name;
        Surname = surname;
    }
}

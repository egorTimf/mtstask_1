package People;


public class Customer extends People {
    private String address;


    public String getAddress() { return address; }
    public void setAddress(String adr) {  address = adr; }

    public Customer(String name, String surname, String address) {
        super(name, surname);
        this.address = address;
    }


}

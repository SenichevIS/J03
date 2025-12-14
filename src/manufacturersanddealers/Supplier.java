package manufacturersanddealers;

public abstract class Supplier {
    private String inn;
    private String name;
    private String address;

    public Supplier(String inn, String name, String address) {
        this.inn = inn;
        this.name = name;
        this.address = address;
    }

    public String getInn() {
        return inn;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public abstract String getManufacturerName();
}
package manufacturersanddealers;

public class Manufacturer extends Supplier {
    public Manufacturer(String inn, String name, String address) {
        super(inn, name, address);
    }

    @Override
    public String getManufacturerName() {
        return getName();
    }
}

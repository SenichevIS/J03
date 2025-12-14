package manufacturersanddealers;

public class Dealer extends Supplier {
    private Manufacturer manufacturer;
    private double markupPercentage;

    public Dealer(String inn, String name, String address,
                  Manufacturer manufacturer, double markupPercentage) {
        super(inn, name, address);
        this.manufacturer = manufacturer;
        this.markupPercentage = markupPercentage;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public double getMarkupPercentage() {
        return markupPercentage;
    }

    @Override
    public String getManufacturerName() {
        return manufacturer.getName();
    }

    public double calculatePriceWithMarkup(double basePrice) {
        return basePrice * (1 + markupPercentage / 100);
    }
}

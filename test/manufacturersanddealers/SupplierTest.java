package manufacturersanddealers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SupplierTest {

    @Test
    @DisplayName("Абстрактный класс Supplier")
    void supplierAbstractClass() {
        Manufacturer manufacturer = new Manufacturer("123456789012", "Samsung", "Seoul");
        Supplier supplier = manufacturer;

        assertEquals("123456789012", supplier.getInn());
        assertEquals("Samsung", supplier.getName());
        assertEquals("Seoul", supplier.getAddress());
        assertNotNull(supplier.getManufacturerName());
    }

    @Test
    @DisplayName("Полиморфизм поставщиков")
    void supplierPolymorphism() {
        Manufacturer manufacturer = new Manufacturer("123456789012", "Samsung", "Seoul");
        Dealer dealer = new Dealer("111222333444", "ТехноМир", "Moscow", manufacturer, 15.0);

        Supplier supplier1 = manufacturer;
        Supplier supplier2 = dealer;

        assertEquals("Samsung", supplier1.getManufacturerName());
        assertEquals("Samsung", supplier2.getManufacturerName());
    }
}

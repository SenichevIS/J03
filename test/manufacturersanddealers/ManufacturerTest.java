package manufacturersanddealers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ManufacturerTest {

    @Test
    @DisplayName("Создание производителя")
    void createManufacturer() {
        Manufacturer manufacturer = new Manufacturer("123456789012", "Samsung", "Seoul");
        assertEquals("123456789012", manufacturer.getInn());
        assertEquals("Samsung", manufacturer.getName());
        assertEquals("Seoul", manufacturer.getAddress());
        assertEquals("Samsung", manufacturer.getManufacturerName());
    }
}

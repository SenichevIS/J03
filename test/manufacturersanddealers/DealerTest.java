package manufacturersanddealers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealerTest {

    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer("123456789012", "Samsung", "Seoul");
    }

    @Test
    @DisplayName("Создание дилера")
    void createDealer() {
        Dealer dealer = new Dealer("111222333444", "ТехноМир", "Moscow", manufacturer, 15.0);
        assertEquals("111222333444", dealer.getInn());
        assertEquals("ТехноМир", dealer.getName());
        assertEquals("Moscow", dealer.getAddress());
        assertEquals(15.0, dealer.getMarkupPercentage());
        assertEquals(manufacturer, dealer.getManufacturer());
        assertEquals("Samsung", dealer.getManufacturerName());
    }

    @Test
    @DisplayName("Расчет цены с наценкой")
    void calculatePriceWithMarkup() {
        Dealer dealer = new Dealer("111222333444", "ТехноМир", "Moscow", manufacturer, 20.0);
        double result = dealer.calculatePriceWithMarkup(100.0);
        assertEquals(120.0, result, 0.001);
    }

    @Test
    @DisplayName("Расчет цены с нулевой наценкой")
    void calculatePriceWithZeroMarkup() {
        Dealer dealer = new Dealer("111222333444", "ТехноМир", "Moscow", manufacturer, 0.0);
        double result = dealer.calculatePriceWithMarkup(100.0);
        assertEquals(100.0, result, 0.001);
    }

    @Test
    @DisplayName("Расчет цены с отрицательной наценкой")
    void calculatePriceWithNegativeMarkup() {
        Dealer dealer = new Dealer("111222333444", "ТехноМир", "Moscow", manufacturer, -10.0);
        double result = dealer.calculatePriceWithMarkup(100.0);
        assertEquals(90.0, result, 0.001);
    }
}

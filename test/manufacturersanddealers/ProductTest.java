package manufacturersanddealers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    private Manufacturer manufacturer;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        manufacturer = new Manufacturer("123456789012", "Samsung", "Seoul");
        dealer = new Dealer("111222333444", "ТехноМир", "Moscow", manufacturer, 20.0);
    }

    @Test
    @DisplayName("Создание товара от производителя")
    void createProductFromManufacturer() {
        Product product = new Product("12345678901234", "Телевизор", 50000.0, manufacturer);
        assertEquals("12345678901234", product.getArticle());
        assertEquals("Телевизор", product.getTitle());
        assertEquals(50000.0, product.getBasePrice(), 0.001);
        assertEquals(manufacturer, product.getSupplier());
        assertEquals(50000.0, product.getFinalPrice(), 0.001);
        assertEquals("Samsung", product.getManufacturerName());
    }

    @Test
    @DisplayName("Создание товара от дилера")
    void createProductFromDealer() {
        Product product = new Product("12345678901234", "Телевизор", 50000.0, dealer);
        assertEquals(60000.0, product.getFinalPrice(), 0.001);
        assertEquals("Samsung", product.getManufacturerName());
    }

    @Test
    @DisplayName("Артикул слишком короткий")
    void articleTooShort() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("123", "Телевизор", 50000.0, manufacturer);
        });
        assertTrue(exception.getMessage().contains("Артикул должен содержать 10-15 цифр"));
    }

    @Test
    @DisplayName("Артикул слишком длинный")
    void articleTooLong() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("1234567890123456", "Телевизор", 50000.0, manufacturer);
        });
        assertTrue(exception.getMessage().contains("Артикул должен содержать 10-15 цифр"));
    }

    @Test
    @DisplayName("Артикул содержит буквы")
    void articleContainsLetters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("1234567890ab", "Телевизор", 50000.0, manufacturer);
        });
        assertTrue(exception.getMessage().contains("Артикул должен содержать только цифры"));
    }

    @Test
    @DisplayName("Артикул содержит спецсимволы")
    void articleContainsSpecialCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("1234567890-12", "Телевизор", 50000.0, manufacturer);
        });
        assertTrue(exception.getMessage().contains("Артикул должен содержать только цифры"));
    }

    @Test
    @DisplayName("Минимальная длина артикула")
    void articleMinLength() {
        Product product = new Product("1234567890", "Телевизор", 50000.0, manufacturer);
        assertEquals("1234567890", product.getArticle());
    }

    @Test
    @DisplayName("Максимальная длина артикула")
    void articleMaxLength() {
        Product product = new Product("123456789012345", "Телевизор", 50000.0, manufacturer);
        assertEquals("123456789012345", product.getArticle());
    }
}


package manufacturersanddealers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductSearchResultTest {

    @Test
    @DisplayName("Создание результата поиска")
    void createSearchResult() {
        ProductSearchResult result = new ProductSearchResult(
                "12345678901234",
                "Samsung TV",
                57500.0,
                "ТехноМир",
                "Moscow",
                "Samsung Electronics"
        );

        assertEquals("12345678901234", result.getArticle());
        assertEquals("Samsung TV", result.getProductTitle());
        assertEquals(57500.0, result.getFinalPrice(), 0.001);
        assertEquals("ТехноМир", result.getSupplierName());
        assertEquals("Moscow", result.getSupplierAddress());
        assertEquals("Samsung Electronics", result.getManufacturerName());
    }

    @Test
    @DisplayName("Строковое представление результата")
    void toStringRepresentation() {
        ProductSearchResult result = new ProductSearchResult(
                "12345678901234",
                "Samsung TV",
                57500.0,
                "ТехноМир",
                "Moscow",
                "Samsung Electronics"
        );

        String str = result.toString();
        assertTrue(str.contains("12345678901234"));
        assertTrue(str.contains("Samsung TV"));
        assertTrue(str.contains("57500.00"));
        assertTrue(str.contains("ТехноМир"));
        assertTrue(str.contains("Moscow"));
        assertTrue(str.contains("Samsung Electronics"));
    }
}

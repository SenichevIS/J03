package manufacturersanddealers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductSearchServiceTest {

    private ProductSearchService searchService;
    private Manufacturer samsung;
    private Dealer dealer;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        samsung = new Manufacturer("123456789012", "Samsung Electronics", "Seoul");
        dealer = new Dealer("111222333444", "ТехноМир", "Moscow", samsung, 15.0);

        product1 = new Product("12345678901234", "Samsung TV QLED 55", 50000.0, dealer);
        product2 = new Product("98765432109876", "Samsung Galaxy S23", 80000.0, samsung);
        product3 = new Product("55555555555555", "LG OLED TV", 60000.0,
                new Manufacturer("999999999999", "LG", "Seoul"));

        searchService = new ProductSearchService(List.of(product1, product2, product3));
    }

    @Test
    @DisplayName("Поиск по полному артикулу")
    void searchByExactArticle() {
        List<ProductSearchResult> results = searchService.searchProducts("12345678901234");
        assertEquals(1, results.size());
        assertEquals("12345678901234", results.get(0).getArticle());
        assertEquals("Samsung TV QLED 55", results.get(0).getProductTitle());
        assertEquals(57500.0, results.get(0).getFinalPrice(), 0.001); // 50000 + 15%
        assertEquals("ТехноМир", results.get(0).getSupplierName());
        assertEquals("Samsung Electronics", results.get(0).getManufacturerName());
    }

    @Test
    @DisplayName("Поиск по части названия (регистр не важен)")
    void searchByPartialNameCaseInsensitive() {
        List<ProductSearchResult> results = searchService.searchProducts("samsung");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("Поиск по части названия")
    void searchByPartialName() {
        List<ProductSearchResult> results = searchService.searchProducts("TV");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("Поиск не найден")
    void searchNotFound() {
        List<ProductSearchResult> results = searchService.searchProducts("iPhone");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Поиск пустой строки")
    void searchEmptyString() {
        List<ProductSearchResult> results = searchService.searchProducts("");
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Поиск null")
    void searchNull() {
        List<ProductSearchResult> results = searchService.searchProducts(null);
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Поиск с пробелами")
    void searchWithSpaces() {
        List<ProductSearchResult> results = searchService.searchProducts("  samsung  ");
        assertEquals(2, results.size());
    }

    @Test
    @DisplayName("Добавление товара и поиск")
    void addProductAndSearch() {
        Product newProduct = new Product("77777777777777", "New Samsung Phone", 30000.0, dealer);
        searchService.addProduct(newProduct);

        List<ProductSearchResult> results = searchService.searchProducts("Phone");
        assertEquals(1, results.size());
        assertEquals("New Samsung Phone", results.get(0).getProductTitle());
    }

    @Test
    @DisplayName("Получение всех товаров")
    void getAllProducts() {
        List<Product> allProducts = searchService.getAllProducts();
        assertEquals(3, allProducts.size());
    }
}
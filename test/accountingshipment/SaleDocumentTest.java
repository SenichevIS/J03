package accountingshipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

class SaleDocumentTest {

    private Storage storage;
    private Customer customer;
    private Product tv;
    private Product fridge;
    private Product washer;

    @BeforeEach
    void setUp() {
        storage = new Storage("Склад 1", "Компания А");
        customer = new Customer("Покупатель");

        tv = new Product("P1", "ART1", "Телевизор", 2, 50000);
        fridge = new Product("P2", "ART2", "Холодильник", 1, 70000);
        washer = new Product("P3", "ART3", "Стиральная машина", 3, 30000);
    }

    @Test
    void testCreateSaleDocument() {
        List<Product> products = Arrays.asList(tv);
        SaleDocument doc = new SaleDocument(
                "D1", new Date(), customer.getName(), storage, products
        );

        assertEquals("D1", doc.getDocumentId());
        assertEquals("Покупатель", doc.getCustomer());
        assertEquals(storage, doc.getStorage());
        assertNull(doc.getMovingStorage());
        assertEquals(1, doc.getProductsList().size());
    }

    @Test
    void testTotalAmount() {
        List<Product> products = Arrays.asList(tv, fridge);
        SaleDocument doc = new SaleDocument(
                "D2", new Date(), customer.getName(), storage, products
        );

        assertEquals(170000, doc.totalAmount());
    }

    @Test
    void testItemAmount() {
        List<Product> products = Arrays.asList(tv, fridge);
        SaleDocument doc = new SaleDocument(
                "D3", new Date(), customer.getName(), storage, products
        );

        assertEquals(100000, doc.itemAmount("P1"));
        assertEquals(70000, doc.itemAmount("P2"));
        assertEquals(0, doc.itemAmount("P99"));
    }

    @Test
    void testPromoSumWithoutDiscount() {
        List<Product> products = Arrays.asList(tv, washer);
        SaleDocument doc = new SaleDocument(
                "D4", new Date(), customer.getName(), storage, products
        );

        String[] promo = {"ART1", "ART3"};
        assertEquals(190000, doc.promoSum(promo));
    }

    @Test
    void testPromoSumWithDiscount() {
        List<Product> products = Arrays.asList(tv);
        SaleDocument doc = new SaleDocument(
                "D5", new Date(), customer.getName(), storage, products
        );

        String[] promo = {"ART1"};
        assertEquals(90000, doc.promoSum(promo, 10));
    }

    @Test
    void testPromoSumWithZeroDiscount() {
        List<Product> products = Arrays.asList(tv);
        SaleDocument doc = new SaleDocument(
                "D6", new Date(), customer.getName(), storage, products
        );

        String[] promo = {"ART1"};
        assertEquals(100000, doc.promoSum(promo, 0));
    }

    @Test
    void testIsWholesaleTrueBySingleProduct() {
        Product bulk = new Product("P4", "ART4", "Товар", 100, 1000);
        List<Product> products = Arrays.asList(bulk);
        SaleDocument doc = new SaleDocument(
                "D7", new Date(), customer.getName(), storage, products
        );

        assertTrue(doc.isWholesale(50));
    }

    @Test
    void testIsWholesaleTrueByTotal() {
        Product item1 = new Product("P4", "ART4", "Товар", 40, 1000);
        Product item2 = new Product("P5", "ART5", "Товар", 40, 1000);
        List<Product> products = Arrays.asList(item1, item2);
        SaleDocument doc = new SaleDocument(
                "D8", new Date(), customer.getName(), storage, products
        );

        assertTrue(doc.isWholesale(70));
    }

    @Test
    void testIsWholesaleFalse() {
        List<Product> products = Arrays.asList(tv);
        SaleDocument doc = new SaleDocument(
                "D9", new Date(), customer.getName(), storage, products
        );

        assertFalse(doc.isWholesale(100));
    }

    @Test
    void testEmptyProducts() {
        List<Product> empty = Arrays.asList();
        SaleDocument doc = new SaleDocument(
                "D10", new Date(), customer.getName(), storage, empty
        );

        assertEquals(0, doc.totalAmount());
        assertEquals(0, doc.promoSum(new String[]{"ART1"}));
        assertEquals(0, doc.promoSum(new String[]{"ART1"}, 10));
        assertFalse(doc.isWholesale(1));
    }

    @Test
    void testPromoSumWithMultipleProductsAndDiscount() {
        Product promoProduct1 = new Product("P1", "ART1", "Товар 1", 2, 10000);
        Product promoProduct2 = new Product("P2", "ART2", "Товар 2", 3, 20000);
        Product nonPromoProduct = new Product("P3", "ART3", "Товар 3", 1, 30000);

        List<Product> products = Arrays.asList(promoProduct1, promoProduct2, nonPromoProduct);
        SaleDocument doc = new SaleDocument(
                "D11", new Date(), customer.getName(), storage, products
        );

        String[] promo = {"ART1", "ART2"};
        assertEquals(72000, doc.promoSum(promo, 10));
    }
}
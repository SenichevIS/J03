package accountingshipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

class MovingDocumentTest {

    private Storage storage1;
    private Storage storage2;
    private Product tv;
    private Product fridge;
    private Product washer;

    @BeforeEach
    void setUp() {
        storage1 = new Storage("Склад 1", "Компания А");
        storage2 = new Storage("Склад 2", "Компания Б");

        tv = new Product("P1", "ART1", "Телевизор", 2, 50000);
        fridge = new Product("P2", "ART2", "Холодильник", 1, 70000);
        washer = new Product("P3", "ART3", "Стиральная машина", 3, 30000);
    }

    @Test
    void testCreateMovingDocument() {
        List<Product> products = Arrays.asList(tv);
        MovingDocument doc = new MovingDocument(
                "D1", new Date(), storage1, storage2, products
        );

        assertEquals("D1", doc.getDocumentId());
        assertEquals(storage1, doc.getStorage());
        assertEquals(storage2, doc.getMovingStorage());
        assertNull(doc.getCustomer());
        assertEquals(1, doc.getProductsList().size());
    }

    @Test
    void testTotalAmount() {
        List<Product> products = Arrays.asList(tv, fridge);
        MovingDocument doc = new MovingDocument(
                "D2", new Date(), storage1, storage2, products
        );

        assertEquals(170000, doc.totalAmount());
    }

    @Test
    void testItemAmount() {
        List<Product> products = Arrays.asList(tv, fridge);
        MovingDocument doc = new MovingDocument(
                "D3", new Date(), storage1, storage2, products
        );

        assertEquals(100000, doc.itemAmount("P1"));
        assertEquals(70000, doc.itemAmount("P2"));
        assertEquals(0, doc.itemAmount("P99"));
    }

    @Test
    void testPromoSumWithoutDiscount() {
        List<Product> products = Arrays.asList(tv, washer);
        MovingDocument doc = new MovingDocument(
                "D4", new Date(), storage1, storage2, products
        );

        String[] promo = {"ART1", "ART3"};
        assertEquals(190000, doc.promoSum(promo));
    }

    @Test
    void testPromoSumIgnoresDiscount() {
        List<Product> products = Arrays.asList(tv);
        MovingDocument doc = new MovingDocument(
                "D5", new Date(), storage1, storage2, products
        );

        String[] promo = {"ART1"};
        assertEquals(100000, doc.promoSum(promo, 10));
        assertEquals(100000, doc.promoSum(promo, 50));
        assertEquals(100000, doc.promoSum(promo, 100));
    }

    @Test
    void testIsInternalMovementTrue() {
        Storage sameOwnerStorage = new Storage("Склад Б", "Компания А");
        List<Product> products = Arrays.asList(tv);

        MovingDocument doc = new MovingDocument(
                "D6", new Date(), storage1, sameOwnerStorage, products
        );

        assertTrue(doc.isInternalMovement());
    }

    @Test
    void testIsInternalMovementFalse() {
        List<Product> products = Arrays.asList(tv);
        MovingDocument doc = new MovingDocument(
                "D7", new Date(), storage1, storage2, products
        );

        assertFalse(doc.isInternalMovement());
    }

    @Test
    void testIsInternalMovementSameStorage() {
        List<Product> products = Arrays.asList(tv);
        MovingDocument doc = new MovingDocument(
                "D8", new Date(), storage1, storage1, products
        );

        assertTrue(doc.isInternalMovement());
    }

    @Test
    void testEmptyProducts() {
        List<Product> empty = Arrays.asList();
        MovingDocument doc = new MovingDocument(
                "D9", new Date(), storage1, storage2, empty
        );

        assertEquals(0, doc.totalAmount());
        assertEquals(0, doc.promoSum(new String[]{"ART1"}));
        assertEquals(0, doc.promoSum(new String[]{"ART1"}, 10));
    }

    @Test
    void testPromoSumWithMultipleProducts() {
        Product promoProduct1 = new Product("P1", "ART1", "Товар 1", 2, 10000);
        Product promoProduct2 = new Product("P2", "ART2", "Товар 2", 3, 20000);
        Product nonPromoProduct = new Product("P3", "ART3", "Товар 3", 1, 30000);

        List<Product> products = Arrays.asList(promoProduct1, promoProduct2, nonPromoProduct);
        MovingDocument doc = new MovingDocument(
                "D10", new Date(), storage1, storage2, products
        );

        String[] promo = {"ART1", "ART2"};
        assertEquals(80000, doc.promoSum(promo, 10));
    }

    @Test
    void testNoCustomerInMovingDocument() {
        List<Product> products = Arrays.asList(tv);
        MovingDocument doc = new MovingDocument(
                "D11", new Date(), storage1, storage2, products
        );

        assertNull(doc.getCustomer());
    }
}
package accountingshipment;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDocumentTest {

    private Storage storage1;
    private Storage storage2;
    private Customer customer;
    private Product tv;
    private Product fridge;
    private Product washer;

    @BeforeEach
    void setUp() {
        storage1 = new Storage("Склад 1", "Компания А");
        storage2 = new Storage("Склад 2", "Компания Б");
        customer = new Customer("Покупатель");

        tv = new Product("P1", "ART1", "Телевизор", 2, 50000);
        fridge = new Product("P2", "ART2", "Холодильник", 1, 70000);
        washer = new Product("P3", "ART3", "Стиральная машина", 3, 30000);
    }

    @Test
    void testTotalAmount() {
        List<Product> products = Arrays.asList(tv, fridge);
        ShipmentDocument doc = createSaleDoc("D1", products);

        assertEquals(170000, doc.totalAmount());
    }

    @Test
    void testItemAmount() {
        List<Product> products = Arrays.asList(tv, fridge);
        ShipmentDocument doc = createSaleDoc("D2", products);

        assertEquals(100000, doc.itemAmount("P1"));
        assertEquals(0, doc.itemAmount("P99"));
    }

    @Test
    void testPromoSumWithoutDiscount() {
        List<Product> products = Arrays.asList(tv, washer);
        ShipmentDocument doc = createSaleDoc("D3", products);

        String[] promo = {"ART1", "ART3"};
        assertEquals(190000, doc.promoSum(promo));
    }

    @Test
    void testPromoSumWithDiscountForSale() {
        List<Product> products = Arrays.asList(tv);
        ShipmentDocument doc = createSaleDoc("D4", products);

        String[] promo = {"ART1"};
        assertEquals(90000, doc.promoSum(promo, 10));
    }

    @Test
    void testPromoSumNoDiscountForMoving() {
        List<Product> products = Arrays.asList(tv);
        ShipmentDocument doc = createMovingDoc("D5", products);

        String[] promo = {"ART1"};
        assertEquals(100000, doc.promoSum(promo, 10));
    }

    @Test
    void testIsWholesaleTrue() {
        Product bulk = new Product("P4", "ART4", "Товар", 100, 1000);
        List<Product> products = Arrays.asList(bulk);
        ShipmentDocument doc = createSaleDoc("D6", products);

        assertTrue(doc.isWholesale(50));
    }

    @Test
    void testIsWholesaleFalseForMoving() {
        ShipmentDocument doc = createMovingDoc("D7", Arrays.asList(tv));
        assertFalse(doc.isWholesale(10));
    }

    @Test
    void testIsInternalMovementTrue() {
        Storage sameOwner1 = new Storage("Склад А", "Один владелец");
        Storage sameOwner2 = new Storage("Склад Б", "Один владелец");

        ShipmentDocument doc = new ShipmentDocument(
                "D8", new Date(), "moving", null,
                sameOwner1, sameOwner2, Arrays.asList(tv));

        assertTrue(doc.isInternalMovement());
    }

    @Test
    void testIsInternalMovementFalseForSale() {
        ShipmentDocument doc = createSaleDoc("D9", Arrays.asList(tv));
        assertFalse(doc.isInternalMovement());
    }

    @Test
    void testEmptyProducts() {
        List<Product> empty = Arrays.asList();
        ShipmentDocument doc = createSaleDoc("D10", empty);

        assertEquals(0, doc.totalAmount());
        assertEquals(0, doc.promoSum(new String[]{"ART1"}));
        assertFalse(doc.isWholesale(1));
    }

    private ShipmentDocument createSaleDoc(String id, List<Product> products) {
        return new ShipmentDocument(
                id, new Date(), "sale", customer.getName(),
                storage1, null, products
        );
    }

    private ShipmentDocument createMovingDoc(String id, List<Product> products) {
        return new ShipmentDocument(
                id, new Date(), "moving", null,
                storage1, storage2, products);
    }
}

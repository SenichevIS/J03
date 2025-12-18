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

    @BeforeEach
    void setUp() {
        storage1 = new Storage("Склад 1", "Компания А");
        storage2 = new Storage("Склад 2", "Компания Б");
        customer = new Customer("Покупатель");

        tv = new Product("P1", "ART1", "Телевизор", 2, 50000);
        fridge = new Product("P2", "ART2", "Холодильник", 1, 70000);
    }

    @Test
    void testPolymorphismTotalAmount() {
        List<Product> products = Arrays.asList(tv, fridge);

        ShipmentDocument saleDoc = new SaleDocument(
                "S1", new Date(), customer.getName(), storage1, products
        );

        ShipmentDocument movingDoc = new MovingDocument(
                "M1", new Date(), storage1, storage2, products
        );

        assertEquals(170000, saleDoc.totalAmount());
        assertEquals(170000, movingDoc.totalAmount());
    }

    @Test
    void testPolymorphismPromoSum() {
        List<Product> products = Arrays.asList(tv);

        ShipmentDocument saleDoc = new SaleDocument(
                "S2", new Date(), customer.getName(), storage1, products
        );

        ShipmentDocument movingDoc = new MovingDocument(
                "M2", new Date(), storage1, storage2, products
        );

        String[] promo = {"ART1"};

        assertEquals(90000, saleDoc.promoSum(promo, 10));

        assertEquals(100000, movingDoc.promoSum(promo, 10));
    }

    @Test
    void testPolymorphismItemAmount() {
        List<Product> products = Arrays.asList(tv, fridge);

        ShipmentDocument saleDoc = new SaleDocument(
                "S3", new Date(), customer.getName(), storage1, products
        );

        ShipmentDocument movingDoc = new MovingDocument(
                "M3", new Date(), storage1, storage2, products
        );

        assertEquals(100000, saleDoc.itemAmount("P1"));
        assertEquals(100000, movingDoc.itemAmount("P1"));
        assertEquals(0, saleDoc.itemAmount("P99"));
        assertEquals(0, movingDoc.itemAmount("P99"));
    }

    @Test
    void testCastingToSpecificTypes() {
        List<Product> products = Arrays.asList(tv);

        ShipmentDocument saleDoc = new SaleDocument(
                "S4", new Date(), customer.getName(), storage1, products
        );

        ShipmentDocument movingDoc = new MovingDocument(
                "M4", new Date(), storage1, storage2, products
        );

        if (saleDoc instanceof SaleDocument) {
            SaleDocument sale = (SaleDocument) saleDoc;
            assertFalse(sale.isWholesale(100));
            assertEquals("Покупатель", sale.getCustomer());
        }

        if (movingDoc instanceof MovingDocument) {
            MovingDocument moving = (MovingDocument) movingDoc;
            assertFalse(moving.isInternalMovement());
            assertEquals(storage2, moving.getMovingStorage());
        }
    }
}

package accountingshipment;

import java.util.*;

/**
 * Документ отгрузки со склада.
 * Бывает двух типов: перемещение (на другой склад) и продажа (покупателю).
 */
public abstract class ShipmentDocument {
    private String documentId; // GUID документа
    private Date documentDate; // дата документа
    private String customer; // получатель (только для продажи)
    private Storage storage; // склад отправления
    private Storage movingStorage; // склад получения
    private List<Product> productsList; // список товаров

    public ShipmentDocument(String documentId, Date documentDate,
                            String customer, Storage storage, Storage movingStorage,
                            List<Product> productsList) {
        this.documentId = documentId;
        this.documentDate = documentDate;
        this.customer = customer;
        this.storage = storage;
        this.movingStorage = movingStorage;
        this.productsList = productsList;
    }

    public String getDocumentId() { return documentId; }
    public Date getDocumentDate() { return documentDate; }
    public String getCustomer() { return customer; }
    public Storage getStorage() { return storage; }
    public Storage getMovingStorage() { return movingStorage; }
    public List<Product> getProductsList() { return productsList; }

    /**
     * Суммарная стоимость товаров в документе.
     */
    double totalAmount() {
        double sum = 0;
        for (Product product : productsList) {
            sum += product.getAmount();
        }
        return sum;
    }

    /**
     * Стоимость товара с переданным id.
     */
    double itemAmount(String id) {
        for (Product product : productsList) {
            if (product.getId().equals(id)) {
                return product.getAmount();
            }
        }
        return 0;
    }

    /**
     * Суммарная стоимость товаров, попадающих в список промо-акции.
     */
    double promoSum(String[] promoArticles) {
        return promoSum(promoArticles, 0);
    }

    abstract double promoSum(String[] promoArticles, double discountPercent);
}

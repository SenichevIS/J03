package accountingshipment;

import java.util.*;

/**
 * Документ отгрузки со склада.
 * Бывает двух типов: перемещение (на другой склад) и продажа (покупателю).
 */
class ShipmentDocument {
    private String documentId; // GUID документа
    private Date documentDate; // дата документа
    private String documentType; // тип отгрузки: sale - продажа, moving - перемещение
    private String customer; // получатель (только для продажи)
    private Storage storage; // склад отправления
    private Storage movingStorage; // склад получения
    private List<Product> productsList; // список товаров

    public ShipmentDocument(String documentId, Date documentDate, String documentType,
                            String customer, Storage storage, Storage movingStorage,
                            List<Product> productsList) {
        this.documentId = documentId;
        this.documentDate = documentDate;
        this.documentType = documentType;
        this.customer = customer;
        this.storage = storage;
        this.movingStorage = movingStorage;
        this.productsList = productsList;
    }

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

    double promoSum(String[] promoArticles, double discountPercent) {
        boolean isSale = documentType.equals("sale");
        double sum = 0;
        for (Product product : productsList) {
            for (String promoArticle : promoArticles) {
                if (product.getArticle().equals(promoArticle)) {
                    double amount = product.getAmount();
                    if (isSale && discountPercent > 0) {
                        double discountedAmount = amount * (1 - discountPercent / 100);
                        discountedAmount = Math.ceil(discountedAmount * 100) / 100.0;
                        sum += discountedAmount;
                    } else {
                        sum += amount;
                    }
                    break;
                }
            }
        }
        return sum;
    }

    /**
     * Является ли продажа оптовой для переданного минимального объема.
     * Для перемещений неприменимо!
     */
    boolean isWholesale(double minQuantity) {
        if (documentType.equals("moving")) {
            return false;
        }
        double sumQuantity = 0;
        for (Product product : productsList) {
            if (product.getQuantity() >= minQuantity) {
                return true;
            }
            sumQuantity += product.getQuantity();
        }
        return sumQuantity >= minQuantity;
    }

    /**
     * Является ли перемещение внутренним (между складами одного владельца).
     * Для продаж неприменимо!
     */
    boolean isInternalMovement() {
        return documentType.equals("moving") &&
                storage.getOwner().equals(movingStorage.getOwner());
    }
}

package accountingshipment;

import java.util.Date;
import java.util.List;

public class SaleDocument extends ShipmentDocument {
    public SaleDocument(String documentId, Date documentDate,
                        String customer, Storage storage,
                        List<Product> productsList) {
        super(documentId, documentDate, customer, storage, null, productsList);
    }

    @Override
    double promoSum(String[] promoArticles, double discountPercent) {
        double sum = 0;
        for (Product product : getProductsList()) {
            for (String promoArticle : promoArticles) {
                if (product.getArticle().equals(promoArticle)) {
                    double amount = product.getAmount();
                    if (discountPercent > 0) {
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

    boolean isWholesale(double minQuantity) {
        double sumQuantity = 0;
        for (Product product : getProductsList()) {
            if (product.getQuantity() >= minQuantity) {
                return true;
            }
            sumQuantity += product.getQuantity();
        }
        return sumQuantity >= minQuantity;
    }
}
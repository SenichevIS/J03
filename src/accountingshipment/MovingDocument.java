package accountingshipment;

import java.util.Date;
import java.util.List;

public class MovingDocument extends ShipmentDocument {
    public MovingDocument(String documentId, Date documentDate,
                          Storage storage, Storage movingStorage,
                          List<Product> productsList) {
        super(documentId, documentDate, null, storage, movingStorage, productsList);
    }

    @Override
    double promoSum(String[] promoArticles, double discountPercent) {
        double sum = 0;
        for (Product product : getProductsList()) {
            for (String promoArticle : promoArticles) {
                if (product.getArticle().equals(promoArticle)) {
                    sum += product.getAmount();
                    break;
                }
            }
        }
        return sum;
    }

    boolean isInternalMovement() {
        return getStorage().getOwner().equals(getMovingStorage().getOwner());
    }
}
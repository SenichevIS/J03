package manufacturersanddealers;

import java.util.Locale;

public class ProductSearchResult {
    private String article;
    private String productTitle;
    private double finalPrice;
    private String supplierName;
    private String supplierAddress;
    private String manufacturerName;

    public ProductSearchResult(String article, String productTitle, double finalPrice,
                               String supplierName, String supplierAddress,
                               String manufacturerName) {
        this.article = article;
        this.productTitle = productTitle;
        this.finalPrice = finalPrice;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.manufacturerName = manufacturerName;
    }

    public String getArticle() {
        return article;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    @Override
    public String toString() {
        String formattedPrice = String.format(Locale.US, "%.2f", finalPrice);

        return String.format(
                "Артикул: %s, Товар: %s, Цена: %s, " +
                        "Поставщик: %s, Адрес поставщика: %s, Производитель: %s",
                article, productTitle, formattedPrice,
                supplierName, supplierAddress, manufacturerName
        );
    }
}

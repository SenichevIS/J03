package manufacturersanddealers;

public class Product {
    private String article;
    private String title;
    private double basePrice;
    private Supplier supplier;

    public Product(String article, String name, double basePrice, Supplier supplier) {
        validateArticle(article);
        this.article = article;
        this.title = name;
        this.basePrice = basePrice;
        this.supplier = supplier;
    }

    public String getArticle() {
        return article;
    }

    public String getTitle() {
        return title;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    private void validateArticle(String article) {
        if (article == null || article.length() < 10 || article.length() > 15) {
            throw new IllegalArgumentException("Артикул должен содержать 10-15 цифр");
        }
        if (!article.matches("\\d+")) {
            throw new IllegalArgumentException("Артикул должен содержать только цифры");
        }
    }

    public double getFinalPrice() {
        if (supplier instanceof Dealer) {
            Dealer dealer = (Dealer) supplier;
            return dealer.calculatePriceWithMarkup(basePrice);
        }
        return basePrice;
    }

    public String getManufacturerName() {
        return supplier.getManufacturerName();
    }
}

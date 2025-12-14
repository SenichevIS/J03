package accountingshipment;

public class Product {
    private final String id; // id продукта
    private String article; // артикул товара
    private String title; // название товара
    private double quantity; // количество шт. товаров
    private double price; // цена товара

    public Product(String id, String article, String title,
                   double quantity, double price){
        this.id = id;
        this.article = article;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getArticle() {
        return article;
    }

    public String getTitle() {
        return title;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount(){
        return Math.round(quantity * price * 100) / 100.0;
    }
}

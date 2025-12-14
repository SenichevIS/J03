package manufacturersanddealers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSearchService {
    private List<Product> products;

    public ProductSearchService(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public List<ProductSearchResult> searchProducts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String searchQuery = query.trim().toLowerCase();

        return products.stream()
                .filter(product -> matchesQuery(product, searchQuery))
                .map(product -> createSearchResult(product))
                .collect(Collectors.toList());
    }

    private boolean matchesQuery(Product product, String query) {
        if (product.getArticle().equals(query)) {
            return true;
        }
        if (product.getTitle().toLowerCase().contains(query)) {
            return true;
        }
        return false;
    }

    private ProductSearchResult createSearchResult(Product product) {
        return new ProductSearchResult(
                product.getArticle(),
                product.getTitle(),
                product.getFinalPrice(),
                product.getSupplier().getName(),
                product.getSupplier().getAddress(),
                product.getManufacturerName()
        );
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}

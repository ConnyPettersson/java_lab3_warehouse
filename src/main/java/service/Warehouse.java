package service;
import entities.Category;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if(product.name() == null || product.name().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }

        for(Product p : products) {
            if(p.id() == product.id()) {
                throw new IllegalArgumentException("Product with the same ID already exists!");
            }
        }
        products.add(product);
    }

    public void getAllProducts() {
        for(Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> getProductSortedByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findFirst();
    }

    public List<Product> getProductsCreatedOn(LocalDate date) {
        return products.stream()
                .filter(product -> product.createdDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<Product> getModifiedProduct() {
        return products.stream()
                .filter(product -> !product.createdDate().equals(product.modifiedDate()))
                .collect(Collectors.toList());
    }

    public void updateProduct(int id, String newName, Category newCategory, Integer newRating) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.id() == id) {
                String updatedName = (newName != null && !newName.isEmpty()) ? newName : p.name();
                Category updatedCategory = (newCategory != null) ? newCategory : p.category();
                int updatedRating = (newRating != null && newRating >= 0 && newRating <= 10) ? newRating : p.rating();

                Product updatedProduct = new Product(
                        p.id(),
                        updatedName,
                        updatedCategory,
                        updatedRating,
                        p.createdDate(),
                        LocalDate.now()
                );

                products.set(i, updatedProduct);
                System.out.println("Product with ID " + id + " updated successfully");
                return;

            }
        }
        throw new IllegalArgumentException("Product with ID " + id + " not found.");
    }
}

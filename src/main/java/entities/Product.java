package entities;

import java.time.LocalDate;

public record Product(int id, String name, Category category, int rating, LocalDate createdDate, LocalDate modifiedDate){
    public Product {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 0 and 10!");
        }
    }
}

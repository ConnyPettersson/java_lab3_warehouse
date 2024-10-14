package entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

public record Product(
        int id,
        @NotEmpty(message = "Product name cannot be empty!")
        String name,

        Category category,

        @Min(value = 0, message = "Rating must be at least 0")
        @Max(value = 10, message = "Rating must be at most 10")
        int rating,

        LocalDate createdDate,
        LocalDate modifiedDate
) {}


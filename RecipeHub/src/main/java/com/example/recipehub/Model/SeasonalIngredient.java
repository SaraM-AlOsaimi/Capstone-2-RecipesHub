package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Check(constraints = "unit='grams' or unit='ml' or unit='cups' or unit='tablespoons' or unit='teaspoons'")
@Check(constraints = "quantity > 0")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonalIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Ingredient name cannot be empty")
    @Size(max = 50, message = "Ingredient name must be less than 50 characters")
    private String name;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive number")
    @Column(columnDefinition = "double not null")
    private Double quantity;

    @NotEmpty(message = "Unit cannot be empty")
    @Pattern(regexp = "grams|ml|cups|tablespoons|teaspoons",
            message = "Unit must be one of: grams, ml, cups, tablespoons, teaspoons")
    @Column(columnDefinition = "varchar(20) not null")
    private String unit;

    @NotNull(message = "Season start month is required")
    @Column(columnDefinition = "int not null")
    @Min(1) @Max(12)
    private Integer seasonStartMonth;

    @NotNull(message = "Season end month is required")
    @Column(columnDefinition = "int not null")
    @Min(1) @Max(12)
    private Integer seasonEndMonth;
}


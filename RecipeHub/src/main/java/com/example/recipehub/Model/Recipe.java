package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User id is null")
    @Column(columnDefinition = "int not null")
    private Integer chefId;

    @NotEmpty(message = "Recipe title is empty")
    @Size(min = 5 , message = "title length should be more than 4 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    // If the recipe has a Seasonal Ingredient in it
    private Integer seasonalIngredientId;

    @NotEmpty(message = "Ingredients cannot be empty")
    @Column(columnDefinition = "text not null")
    private String ingredients;

    private String cookingMethod;

    @Column(columnDefinition = "time")
    private Time cookingTime;

    @NotEmpty(message = "Recipe steps cant be empty")
    @Size(min = 21 , message = "Steps length should be more than 20 characters")
    @Column(columnDefinition = "text not null")
    private String steps;

    @Embedded
    private NutritionalValue nutritionalValue;

    @NotEmpty(message = "category is null")
    @Column(columnDefinition = "varchar(20) not null")
    private String category;

    // recipes that are for sales :
    @Column(columnDefinition = "boolean default false")
    private Boolean isForSale; // Indicates whether the recipe is available for sale or not

    @Positive(message = "Price must be a positive number")
    private Double price; // Price of the recipe if it's available for sale

    @Positive(message = "Quantity must be a positive number")
    private Integer quantity; // quantity available from that recipes ,, if it's for sale


}

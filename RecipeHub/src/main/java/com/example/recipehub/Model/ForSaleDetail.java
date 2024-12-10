package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForSaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(columnDefinition = "int not null")
    private Integer recipeId;

    @Positive(message = "Price must be a positive number")
    private Double price; // Price of the recipe if it's available for sale

    @Positive(message = "Quantity must be a positive number")
    private Integer quantity; // quantity available from that recipes ,, if it's for sale

    @NotNull(message = "User id is null")
    @Column(columnDefinition = "int not null")
    private Integer chefId;
}

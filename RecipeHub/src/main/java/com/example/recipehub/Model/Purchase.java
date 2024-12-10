package com.example.recipehub.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User id is null")
    @Column(columnDefinition = "int not null")
    private Integer userId; // User who purchased the recipe

    @NotNull(message = "cookerId is null")
    @Column(columnDefinition = "int not null")
    private Integer chefId; // chef id

    @NotNull(message = "recipe id is null")
    @Column(columnDefinition = "int not null")
    private Integer recipeId; // Recipe that was purchased

    @Pattern(regexp = "^request-received|prepared|on-the-way|delivered")
    @Column(columnDefinition = "varchar(100)")
    private String status;

    @NotEmpty(message = "Location is empty")
    @Column(columnDefinition = "varchar(15) not null")
    private String userLocation;

    @Positive(message = "Quantity must be a positive number")
    private Integer quantity;

}


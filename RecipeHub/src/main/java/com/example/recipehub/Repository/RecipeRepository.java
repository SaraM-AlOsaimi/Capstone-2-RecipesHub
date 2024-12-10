package com.example.recipehub.Repository;

import com.example.recipehub.Model.Recipe;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

    Recipe findRecipeById(Integer id);

    List<Recipe> findRecipesByCategory(String category);

    List<Recipe> findRecipesByIsForSaleTrue();

    @Query("SELECT r FROM Recipe r WHERE r.nutritionalValue.calories < :calories")
   List<Recipe> findLowCalorieRecipes(Integer calories);

    @Query("SELECT r FROM Recipe r WHERE r.nutritionalValue.protein > :protein")
    List<Recipe> findHighProteinRecipes(Integer protein);


    @Query("SELECT r FROM Recipe r WHERE r.nutritionalValue.protein >= :protein AND r.nutritionalValue.carbs <= :carbs")
    List<Recipe> findBalancedNutritionRecipes(Integer protein,Integer carbs);

    // Find recipes with high calories for weight gain
    @Query("SELECT r FROM Recipe r WHERE r.nutritionalValue.calories > :calories")
    List<Recipe> findHighCalorieRecipes(Integer calories);

    // Find Recipes that have Specific ingredient
    @Query("select r from Recipe r where lower(r.ingredients) like lower(CONCAT('%', :ingredient, '%'))")
    List<Recipe> getRecipeByIngredient(String ingredient);

    // find recipes that does not have Specific ingredient
    @Query("SELECT r FROM Recipe r WHERE lower(r.ingredients) NOT LIKE lower(CONCAT('%', :ingredient, '%'))")
    List<Recipe> findRecipesWithoutIngredient(String ingredient);


}

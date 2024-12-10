package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.Recipe;
import com.example.recipehub.Service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipes-hub/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    // CRUD
    @GetMapping("/get")
    public ResponseEntity<?> getAllRecipes(){
        return ResponseEntity.status(200).body(recipeService.getAllRecipes());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe){
        recipeService.addRecipe(recipe);
        return ResponseEntity.status(200).body(new ApiResponse("Recipe added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Integer id,@RequestBody Recipe recipe){
        recipeService.updateRecipe(id, recipe);
        return ResponseEntity.status(200).body(new ApiResponse("Recipe updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(200).body(new ApiResponse("Recipe deleted"));
    }

    //-------------------------------

    @GetMapping("/get-by-category/{category}")
    public ResponseEntity<?> getRecipesByCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(recipeService.findRecipesByCategory(category));
    }

    //-----------------------------------------------

    // 5- Endpoint :
    //endpoint to find all recipes that available for sale
    @GetMapping("/recipes-for-sale")
    public ResponseEntity<?> getAllRecipesForSale(){
        return ResponseEntity.status(200).body(recipeService.getAllRecipesForSale());
    }

    // 6- Endpoint :
    // update recipe to be it for sale.
    @PutMapping("/update-sale-status/{id}")
    public ResponseEntity<?> updateRecipeForSale(@PathVariable Integer id){
        recipeService.updateRecipeForSale(id);
        return ResponseEntity.status(200).body(new ApiResponse("Recipe sale status updated"));
    }

    // 7- Endpoint :
    // method take user goal and then fetch recommended recipes based on that goal:
    @GetMapping("/get-recipes-by/{goal}")
    public ResponseEntity<?> recommendedRecipe(@PathVariable String goal){
        return ResponseEntity.status(200).body(recipeService.recommendedRecipe(goal));
    }

    // 8- Endpoint :
    // Endpoint to get recipes with ingredient name
    @GetMapping("/search/{ingredient}")
    public ResponseEntity<?> getRecipeByIngredient(@PathVariable String ingredient){
        List<Recipe> recipes = recipeService.getRecipeByIngredient(ingredient);
        return ResponseEntity.status(200).body(recipes);
    }

    // Endpoint
    // Find Recipes that do not contain a specific ingredient
    @GetMapping("/recipes-without/{ingredient}")
    public ResponseEntity<?> getRecipeDontHaveIngredient(@PathVariable String ingredient){
        List<Recipe> recipes = recipeService.getRecipeDontHaveIngredient(ingredient);
        return ResponseEntity.status(200).body(recipes);
    }

}

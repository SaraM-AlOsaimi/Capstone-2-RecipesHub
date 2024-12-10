package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Recipe;
import com.example.recipehub.Repository.ForSaleDetailRepository;
import com.example.recipehub.Repository.RecipeRepository;
import com.example.recipehub.Repository.UserHealthRepository;
import com.example.recipehub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    //--CRUD

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    // add new recipe
    public void addRecipe(Recipe recipe){
        if(!userRepository.existsById(recipe.getChefId())){
           throw new ApiException("Chef not found");
        }
        recipeRepository.save(recipe);
    }

    // update
    public void updateRecipe(Integer id, Recipe recipe){
        Recipe oldRecipe = recipeRepository.findRecipeById(id);
        if(!userRepository.existsById(recipe.getChefId())){
            throw new ApiException("User not found");
        }
        if(oldRecipe==null){
            throw new ApiException("Recipe not found");
        }
        oldRecipe.setCookingMethod(recipe.getCookingMethod());
        oldRecipe.setCookingTime(recipe.getCookingTime());
        oldRecipe.setIngredients(recipe.getIngredients());
        oldRecipe.setTitle(recipe.getTitle());
        oldRecipe.setIsForSale(recipe.getIsForSale());
        oldRecipe.setNutritionalValue(recipe.getNutritionalValue());
        oldRecipe.setSeasonalIngredientId(recipe.getSeasonalIngredientId());
        oldRecipe.setSteps(recipe.getSteps());
        recipeRepository.save(oldRecipe);
    }

    // delete
    public void deleteRecipe(Integer id){
        Recipe recipe = recipeRepository.findRecipeById(id);
        if(recipe==null){
            throw new ApiException("Recipe not found");
        }
        recipeRepository.delete(recipe);
    }
//-------------------------------------------------

    public List<Recipe> findRecipesByCategory(String category) {
        List<Recipe> recipes = recipeRepository.findRecipesByCategory(category);
        if(recipes == null){
            throw new ApiException("No recipes found");
        }
        return recipes;
    }

    // ---------------------------------------------
    // 5- Endpoint :
    // find all recipes that available for sale.
    public List<Recipe> getAllRecipesForSale(){
        List<Recipe> recipes = recipeRepository.findRecipesByIsForSaleTrue();
        if(recipes==null){
            throw new ApiException("No recipes found");
        }
        return recipes;
    }

    // 6- Endpoint :
    // update recipe to be it for sale.
    public void updateRecipeForSale(Integer id){
       Recipe recipe = recipeRepository.findRecipeById(id);
        if(recipe==null){
            throw new ApiException("Recipe not found");
        }
        if(recipe.getIsForSale()){
            throw new ApiException("Recipe already for sale!");
        }
        recipe.setIsForSale(true);
        recipeRepository.save(recipe);
    }

    // 7- Endpoint :
    // method take user goal and then fetch recommended recipes based on that goal:
    public List<Recipe> recommendedRecipe(String goal){
        List<Recipe> recipes;
        switch (goal.toLowerCase()) {
            case "lose weight":
                recipes = recipeRepository.findLowCalorieRecipes(100);
                break;
            case "gain muscle":
                recipes = recipeRepository.findHighProteinRecipes(30);
                break;
            case "delicious food":
                recipes = recipeRepository.findAll();
                break;
            case "maintaining weight":
                recipes = recipeRepository.findBalancedNutritionRecipes(20, 100);
                break;
            case "gain weight":
                recipes = recipeRepository.findHighCalorieRecipes(500);
                break;
            default:
                throw new IllegalStateException("Unexpected value chose from: delicious food,lose weight,gain weight,gain muscle,maintaining weight" + goal.toLowerCase());
        }
    return recipes;
}

   // 8-Endpoint :
   // get recipe by one ingredient name
    public List<Recipe> getRecipeByIngredient(String ingredient){
       List<Recipe> recipes = recipeRepository.getRecipeByIngredient(ingredient);
       if(recipes==null){
           throw new ApiException("no recipes found");
       }
       return recipes;
    }

    // Endpoint
     // Find Recipes that do not contain a specific ingredient
     public List<Recipe> getRecipeDontHaveIngredient(String ingredient){
         List<Recipe> recipes = recipeRepository.findRecipesWithoutIngredient(ingredient);
         if(recipes==null){
             throw new ApiException("no recipes found");
         }
         return recipes;
     }



}

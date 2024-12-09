package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Chef;
import com.example.recipehub.Model.Recipe;
import com.example.recipehub.Repository.ChefRepository;
import com.example.recipehub.Repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChefService {

    private final ChefRepository chefRepository;
    private final RecipeRepository recipeRepository;

    public List<Chef> getAllChefs(){
        return chefRepository.findAll();
    }

    public void addChef(Chef chef){
        chef.setCreatedAt(LocalDateTime.now());
          chefRepository.save(chef);
    }

    public void updateChef(Integer id , Chef chef){
        Chef oldChef = chefRepository.findChefById(id);
        if(oldChef==null){
            throw new ApiException("Chef not found");
        }
        oldChef.setName(chef.getName());
        oldChef.setEmail(chef.getEmail());
        oldChef.setPassword(chef.getPassword());
        oldChef.setUsername(chef.getUsername());
        oldChef.setProfile(chef.getProfile());
        oldChef.setBalance(chef.getBalance());
        chefRepository.save(oldChef);
    }

    public void deleteChef(Integer id){
        Chef chef = chefRepository.findChefById(id);
        if(chef==null){
            throw new ApiException("Chef not found");
        }
    }
    //-------------------------------

    // 1-Endpoint :
    // get Chefs who have balance above (var double)
    public List<Chef> getChefsByBalanceAbove(Double balance){
        List<Chef> chefs = chefRepository.getChefsByBalanceAbove(balance);
        if(chefs==null){
            throw new ApiException("No chefs found");
        }
        return chefs;
    }

    // 4-Endpoint :
    // chef do discount 50% on one of his recipes if the quantity 3 or less
    // 1- take chef id
    // ensure chef exits
    // apply discount for all recipes that have quantity three or less
    public void applyDiscountForLowQuantity(Integer chefId){
      if(!chefRepository.existsById(chefId)){
          throw new ApiException("Chef not found");
      }
      List<Recipe> recipes = chefRepository.findLowQuantityRecipesByChefId(chefId);
      if(recipes==null){
          throw new ApiException("No recipes with low quantity");
      }
      for (Recipe recipe : recipes){
          recipe.setPrice(recipe.getPrice() * 0.5);
          recipeRepository.save(recipe);
      }
    }

    // 15- Endpoint:
    public void markRecipeAsSoldOut(Integer chefId, Integer recipeId) {
        Chef chef = chefRepository.findChefById(chefId);
        if(chef==null){
            throw new ApiException("Chef not found");
        }
        Recipe recipe = recipeRepository.findRecipeById(recipeId);
        if(recipe==null){
            throw new ApiException("recipe not found");
        }
        if (!recipe.getChefId().equals(chefId)) {
            throw new ApiException("This recipe does not belong to the chef");
        }
        recipe.setQuantity(0);
        recipe.setIsForSale(false);
        recipeRepository.save(recipe);
    }

}

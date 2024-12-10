package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Chef;
import com.example.recipehub.Model.ForSaleDetail;
import com.example.recipehub.Model.Recipe;
import com.example.recipehub.Repository.ChefRepository;
import com.example.recipehub.Repository.ForSaleDetailRepository;
import com.example.recipehub.Repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForSaleDetailService {

    private final ForSaleDetailRepository forSaleDetailRepository;
    private final ChefRepository chefRepository;
    private final RecipeRepository recipeRepository;


    public void addSaleDetail(ForSaleDetail forSaleDetail){
        Recipe recipe = recipeRepository.findRecipeById(forSaleDetail.getRecipeId());
        if(!recipe.getChefId().equals(forSaleDetail.getChefId())){
            throw new ApiException("recipe does not belong for this chef");
        }
        forSaleDetailRepository.save(forSaleDetail);
    }

    public void updateSaleDetail(Integer id, ForSaleDetail forSaleDetail){
        ForSaleDetail forSaleDetail1 = forSaleDetailRepository.findForSaleDetailById(id);
        if(forSaleDetail1==null){
            throw new ApiException("Not found");
        }
        forSaleDetail1.setQuantity(forSaleDetail.getQuantity());
        forSaleDetail1.setPrice(forSaleDetail1.getPrice());
        forSaleDetailRepository.save(forSaleDetail1);
    }

    public void deleteSaleDetail(Integer id){
        ForSaleDetail forSaleDetail = forSaleDetailRepository.findForSaleDetailById(id);
        if(forSaleDetail==null){
            throw new ApiException("Not found");
        }
        forSaleDetailRepository.delete(forSaleDetail);
    }


    //---------------------------
    // 4-Endpoint :

    public void applyDiscountForLowQuantity(Integer chefId) {
        // Ensure the chef exists
        if (!chefRepository.existsById(chefId)) {
            throw new ApiException("Chef not found");
        }

        List<ForSaleDetail> lowQuantityRecipes = forSaleDetailRepository.findLowQuantityRecipesByChefId(chefId);

        if (lowQuantityRecipes.isEmpty()) {
            throw new ApiException("No recipes with low quantity");
        }

        for (ForSaleDetail recipe : lowQuantityRecipes) {
            recipe.setPrice(recipe.getPrice() * 0.5);
        }

        forSaleDetailRepository.saveAll(lowQuantityRecipes);
    }

    // 15- Endpoint:
    public void markRecipeAsSoldOut(Integer chefId, Integer recipeId) {
        Chef chef = chefRepository.findChefById(chefId);
        if(chef==null){
            throw new ApiException("Chef not found");
        }
        ForSaleDetail recipe = forSaleDetailRepository.findForSaleDetailById(recipeId);
        if(recipe==null){
            throw new ApiException("recipe not found");
        }
        if (!recipe.getChefId().equals(chefId)) {
            throw new ApiException("This recipe does not belong to the chef");
        }
        recipe.setQuantity(0);
        forSaleDetailRepository.save(recipe);
    }

}

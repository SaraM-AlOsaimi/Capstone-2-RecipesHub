package com.example.recipehub.Service;


import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.*;
import com.example.recipehub.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
//    private final RecipeRepository recipeRepository;
    private final ChefRepository chefRepository;
    private final ForSaleDetailRepository forSaleDetailRepository;

    public List<Purchase> getAllPurchase() {
        return purchaseRepository.findAll();
    }

    // 12- Endpoint :
    // Create Purchase ,, User buy a meal from chef
    public void buyMeal(Purchase purchase) {
     User user = userRepository.findUserById(purchase.getUserId());
      if(user==null){
       throw new ApiException("User not found");
      }
      ForSaleDetail recipe = forSaleDetailRepository.findForSaleDetailByRecipeId(purchase.getRecipeId());
      if(recipe==null){
          throw new ApiException("Recipe not found");
      }
      Chef chef = chefRepository.findChefById(purchase.getChefId());
      if(chef==null || !recipe.getChefId().equals(purchase.getChefId())){
          throw new ApiException("Chef is invalid");
      }
      if(user.getBalance() < recipe.getPrice()){
         throw new ApiException("Inefficient balance") ;
      }
      user.setBalance(user.getBalance() - recipe.getPrice());
      chef.setBalance(recipe.getPrice());
      recipe.setQuantity(recipe.getQuantity()-1);
      purchase.setStatus("request-received");
      userRepository.save(user);
      chefRepository.save(chef);
      forSaleDetailRepository.save(recipe);
      purchaseRepository.save(purchase);
    }

    // 13- Endpoint
    // Update status
    public void updatePurchaseStatus(Integer purchaseId, String newStatus, Integer chefId) {
        if (!newStatus.matches("^(request-received|prepared|on-the-way|delivered)$")) {
            throw new ApiException("Invalid status: Status must be one of 'request-received', 'prepared', 'on-the-way', or 'delivered'.");
        }
        Purchase existingPurchase = purchaseRepository.findPurchaseRecipeById(purchaseId);
        if (existingPurchase == null) {
            throw new ApiException("Purchase not found");
        }

        if (!existingPurchase.getChefId().equals(chefId)) {
            throw new ApiException("Unauthorized: You can only update your own purchase.");
        }

        existingPurchase.setStatus(newStatus);
        purchaseRepository.save(existingPurchase);
    }

    // 14- Endpoint:
    // Delete Purchase ,, Cancel
    public void deletePurchase(Integer purchaseId) {
        Purchase purchase = purchaseRepository.findPurchaseRecipeById(purchaseId);
        if(purchase == null){
            throw new ApiException("Purchase not found");
        }
        User user = userRepository.findUserById(purchase.getUserId());
        ForSaleDetail recipe = forSaleDetailRepository.findForSaleDetailByRecipeId(purchase.getRecipeId());
        Chef chef = chefRepository.findChefById(purchase.getChefId());

        if (purchase.getStatus().equals("request-received")) {
            user.setBalance(user.getBalance() + recipe.getPrice());
            chef.setBalance(chef.getBalance() - recipe.getPrice());
            recipe.setQuantity(recipe.getQuantity() + 1);
//            purchase.setStatus("canceled");
            userRepository.save(user);
            chefRepository.save(chef);
            forSaleDetailRepository.save(recipe);
        }
        purchaseRepository.delete(purchase);
    }

}

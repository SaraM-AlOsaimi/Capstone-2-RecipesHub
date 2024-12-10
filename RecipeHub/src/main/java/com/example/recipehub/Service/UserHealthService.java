package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Recipe;
import com.example.recipehub.Model.UserHealth;
import com.example.recipehub.Repository.RecipeRepository;
import com.example.recipehub.Repository.UserHealthRepository;
import com.example.recipehub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHealthService {

    private final UserHealthRepository userHealthRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;


    //-----CRUD
    public List<UserHealth> getAllUsersHealth(){
        return userHealthRepository.findAll();
    }

    public void addUserHealth(UserHealth userHealth){
        if(!userRepository.existsById(userHealth.getUserId())){
            throw new ApiException("User not found");
        }
        if (userHealthRepository.findUserHealthById(userHealth.getUserId()) != null) {
            throw new ApiException("Health profile for this user already exists.");
        }
        userHealthRepository.save(userHealth);
    }

    public void updateUserHealth(Integer id, UserHealth userHealth){
        UserHealth oldUserHealth = userHealthRepository.findUserHealthById(id);
        if(!userRepository.existsById(userHealth.getUserId())){
            throw new ApiException("User not found");
        }
        oldUserHealth.setAge(userHealth.getAge());
        oldUserHealth.setHeight(userHealth.getHeight());
        oldUserHealth.setWeight(userHealth.getWeight());
        oldUserHealth.setHasDiabetes(userHealth.getHasDiabetes());
        oldUserHealth.setOtherHealthConditions(userHealth.getOtherHealthConditions());
        oldUserHealth.setHasHypertension(userHealth.getHasHypertension());
        oldUserHealth.setOtherHealthConditions(userHealth.getOtherHealthConditions());
        oldUserHealth.setFoodAllergies(userHealth.getFoodAllergies());
        oldUserHealth.setHasHeartDisease(userHealth.getHasHeartDisease());
        userHealthRepository.save(oldUserHealth);
    }

    public void deleteUserHealth(Integer id){
        UserHealth userHealth = userHealthRepository.findUserHealthById(id);
        if(!userRepository.existsById(userHealth.getUserId())){
            throw new ApiException("User not found");
        }
        userHealthRepository.delete(userHealth);
    }

    //---------------------------

    // Endpoint :
    // get all Users that have Diabetes
    public List<UserHealth> getUserThatHasDiabetes(Boolean diabetes){
        List<UserHealth> userHealth = userHealthRepository.getUserHealthByHasDiabetes(diabetes);
        if(userHealth==null){
            throw new ApiException("No users found");
        }
        return userHealth;
    }


    // Endpoint
    // Find Recipes Based on User Health Profile .. Diabetes
//    public List<Recipe> getRecipesForUserHealth(Integer userId) {
//        UserHealth userHealth = userHealthRepository.findUserHealthById(userId);
//        if (userHealth == null) {
//            throw new ApiException("User health profile not found");
//        }
//        List<Recipe> recipes;
//        if (userHealth.getHasDiabetes()) {
//            recipes = recipeRepository.findLowSugarRecipes(5.0);
//        } else {
//            recipes = recipeRepository.findAll();
//        }
//        if (recipes.isEmpty()) {
//            throw new ApiException("No recipes found based on the user's health profile");
//        }
//        return recipes;
//    }

}

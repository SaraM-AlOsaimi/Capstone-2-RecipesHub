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





}

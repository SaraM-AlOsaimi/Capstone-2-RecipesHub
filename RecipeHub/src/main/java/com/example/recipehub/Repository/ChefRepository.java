package com.example.recipehub.Repository;

import com.example.recipehub.Model.Chef;
import com.example.recipehub.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRepository extends JpaRepository<Chef,Integer> {

    Chef findChefById(Integer id);

    @Query("select c from Chef c where c.balance > ?1")
    List<Chef> getChefsByBalanceAbove(Double balance);


    @Query("select r from Recipe r where r.quantity <= 3 and r.chefId=?1")
    List<Recipe> findLowQuantityRecipesByChefId(Integer chefId);

}

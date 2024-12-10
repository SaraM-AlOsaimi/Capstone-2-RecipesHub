package com.example.recipehub.Repository;

import com.example.recipehub.Model.ForSaleDetail;
import com.example.recipehub.Model.Recipe;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForSaleDetailRepository extends JpaRepository<ForSaleDetail,Integer> {


    ForSaleDetail findForSaleDetailById(Integer id);

    @Query("SELECT r FROM ForSaleDetail r WHERE r.chefId = :chefId AND r.quantity <= 3")
    List<ForSaleDetail> findLowQuantityRecipesByChefId(Integer chefId);

    ForSaleDetail findForSaleDetailByRecipeId(Integer recipeId);
}

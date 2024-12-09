package com.example.recipehub.Repository;

import com.example.recipehub.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {

    Purchase findPurchaseRecipeById(Integer id);

    Purchase findAllByStatus(String status);


    @Query("select p from Purchase p where p.status='delivered'")
    List<Purchase> getPurchasesByStatus(String status);

}

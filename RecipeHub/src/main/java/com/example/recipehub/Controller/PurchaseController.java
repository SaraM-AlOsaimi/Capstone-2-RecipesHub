package com.example.recipehub.Controller;
import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.Purchase;
import com.example.recipehub.Service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

        private final PurchaseService purchaseService;

        // 12- Get all purchases
        @GetMapping("/get")
        public ResponseEntity<?> getAllPurchases() {
            List<Purchase> purchases = purchaseService.getAllPurchase();
            return ResponseEntity.status(200).body(purchases);
        }

        // 13- Create Purchase (User buys a recipe)
        @PostMapping("/add")
        public ResponseEntity<?> buyMeal(@RequestBody Purchase purchase) {
            purchaseService.buyMeal(purchase);
            return ResponseEntity.status(200).body(new ApiResponse("Purchase created successfully"));
        }

        // 14- Update Purchase (Change status, etc.)
        @PutMapping("update/{id}/{newStatus}/{chefId}")
        public ResponseEntity<?> updatePurchase(@PathVariable Integer id,@PathVariable String newStatus,@PathVariable Integer chefId) {
            purchaseService.updatePurchaseStatus(id,newStatus,chefId);
            return ResponseEntity.status(200).body(new ApiResponse("Purchase updated successfully"));
        }

        // 15- Delete Purchase (Cancel purchase)
        @DeleteMapping("delete/{id}")
        public ResponseEntity<ApiResponse> deletePurchase(@PathVariable Integer id) {
            purchaseService.deletePurchase(id);
            return ResponseEntity.status(200).body(new ApiResponse("Purchase deleted successfully"));
        }

    }



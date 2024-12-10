package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.ForSaleDetail;
import com.example.recipehub.Service.ForSaleDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sale-detail")
public class ForSaleDetailController {

    private final ForSaleDetailService forSaleDetailService;

    @PostMapping("/add")
    public ResponseEntity<?> addSaleInfo(@RequestBody @Valid ForSaleDetail forSaleDetail , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        forSaleDetailService.addSaleDetail(forSaleDetail);
        return ResponseEntity.status(200).body(new ApiResponse("Sale detail added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSaleInfo(@PathVariable Integer id,@RequestBody @Valid ForSaleDetail forSaleDetail , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        forSaleDetailService.updateSaleDetail(id,forSaleDetail);
        return ResponseEntity.status(200).body(new ApiResponse("Sale detail updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> updateSaleInfo(@PathVariable Integer id){
        forSaleDetailService.deleteSaleDetail(id);
        return ResponseEntity.status(200).body(new ApiResponse("Sale detail deleted"));
    }

    //---------------------------
    // 4- Endpoint :
    // chef do discount 50% on one of his recipes if the quantity 3 or less
    @PutMapping("/apply-discount/chef-id/{id}")
    public ResponseEntity<?> applyDiscountForLowQuantity(@PathVariable Integer id){
        forSaleDetailService.applyDiscountForLowQuantity(id);
        return ResponseEntity.status(200).body(new ApiResponse("Discount applied"));
    }

    // 15- Endpoint:
    @PostMapping("/chef/{chefId}/recipe/{recipeId}/sold-out")
    public ResponseEntity<String> markRecipeAsSoldOut(@PathVariable Integer chefId, @PathVariable Integer recipeId) {
        forSaleDetailService.markRecipeAsSoldOut(chefId, recipeId);
        return ResponseEntity.status(200).body("Marked as Sold out");
    }
}

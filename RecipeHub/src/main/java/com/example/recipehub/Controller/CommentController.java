package com.example.recipehub.Controller;

import com.example.recipehub.API.ApiResponse;
import com.example.recipehub.Model.Comment;
import com.example.recipehub.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipe-hub/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //--CRUD
    @GetMapping("/get")
    public ResponseEntity<?> getAllComments(){
        return ResponseEntity.status(200).body(commentService.getAllComment());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody @Valid Comment comment , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id,@RequestBody @Valid Comment comment , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id,comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted"));
    }

    //---------------------------

    // 2-Endpoint :
    // get all Comment on one recipe using recipe ID
    @GetMapping("/get-comments-by-recipe/{id}")
    public ResponseEntity<?> getAllCommentOnOneRecipes(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getAllCommentOnOneRecipe(id));
    }

}

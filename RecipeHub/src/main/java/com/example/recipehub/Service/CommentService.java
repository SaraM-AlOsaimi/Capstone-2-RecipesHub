package com.example.recipehub.Service;

import com.example.recipehub.API.ApiException;
import com.example.recipehub.Model.Comment;
import com.example.recipehub.Repository.CommentRepository;
import com.example.recipehub.Repository.RecipeRepository;
import com.example.recipehub.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;


    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }

    //----CRUD
    public void addComment(Comment comment){
        if(!userRepository.existsById(comment.getUserId())){
            throw new ApiException("User not found");
        }
        if(!recipeRepository.existsById(comment.getRecipeId())){
            throw new ApiException("Recipe not found");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Integer id,Comment comment){
       Comment oldComment  = commentRepository.findCommentById(id);
        if(comment==null){
            throw new ApiException("Comment not found");
        }
        oldComment.setComment(comment.getComment());
        commentRepository.save(comment);
    }

    public void deleteComment(Integer id){
        Comment comment = commentRepository.findCommentById(id);
        if(comment==null){
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(comment);
    }

    //------------------------------------------------
    // 2-Endpoint :
    // get all Comment on one recipe using recipe ID
    public List<Comment> getAllCommentOnOneRecipe(Integer id){
        List<Comment> comments = commentRepository.getCommentsByRecipeId(id);
        if(comments==null){
            throw new ApiException("No comments found");
        }
        return comments;
    }

}

package com.example.recipehub.Repository;

import com.example.recipehub.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    Comment findCommentById(Integer id);

    // get all comments on one recipe using recipe ID
    List<Comment> getCommentsByRecipeId(Integer recipeId);

}

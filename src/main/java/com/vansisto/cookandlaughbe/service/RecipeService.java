package com.vansisto.cookandlaughbe.service;

import com.vansisto.cookandlaughbe.entity.Recipe;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface RecipeService {
    void updateRecipeThumbnail(MultipartFile file, Integer recipeId, Authentication authentication);
    Recipe findById(Integer recipeId);
    boolean existsById(Integer recipeId);
}

package com.vansisto.cookandlaughbe.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface RecipeImagesService {
    void uploadRecipeImage(MultipartFile file, Integer recipeId, Authentication authentication);
}

package com.vansisto.cookandlaughbe.controller;

import com.vansisto.cookandlaughbe.service.RecipeImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe-images")
public class RecipeImagesController {
    private final RecipeImagesService recipeImagesService;

    @PostMapping(value = "/upload/{recipeId}", consumes = "multipart/form-data")
    public void uploadRecipeImage(
            @PathVariable Integer recipeId,
            @RequestPart MultipartFile file,
            Authentication authentication) {
        recipeImagesService.uploadRecipeImage(file, recipeId, authentication);
    }

}

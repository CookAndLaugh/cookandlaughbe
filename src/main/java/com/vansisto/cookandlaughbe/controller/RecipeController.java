package com.vansisto.cookandlaughbe.controller;

import com.vansisto.cookandlaughbe.service.RecipeService;
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
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping(value = "/thumbnail/{recipeId}", consumes = "multipart/form-data")
    public Object uploadThumbnail(
            @PathVariable Integer recipeId,
            @RequestPart MultipartFile file,
            Authentication authentication) {
        recipeService.updateRecipeThumbnail(file, recipeId, authentication);
        return null;
    }

}

package com.vansisto.cookandlaughbe.service.impl;

import com.vansisto.cookandlaughbe.aws.CNLS3Client;
import com.vansisto.cookandlaughbe.config.properties.AwsProperties;
import com.vansisto.cookandlaughbe.entity.Recipe;
import com.vansisto.cookandlaughbe.entity.RecipeImage;
import com.vansisto.cookandlaughbe.entity.User;
import com.vansisto.cookandlaughbe.exception.not_found.RecipeNotFoundException;
import com.vansisto.cookandlaughbe.repository.RecipeImagesRepository;
import com.vansisto.cookandlaughbe.repository.RecipeRepository;
import com.vansisto.cookandlaughbe.service.RecipeImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.vansisto.cookandlaughbe.misc.Constants.THUMBNAIL_OBJECT_KEY_PATTERN;

@Service
@RequiredArgsConstructor
public class RecipeImagesServiceImpl implements RecipeImagesService {

    private final RecipeImagesRepository recipeImagesRepository;
    private final RecipeRepository recipeRepository;
    private final CNLS3Client cnlS3Client;
    private final AwsProperties awsProperties;

    @Override
    @Transactional
    public void uploadRecipeImage(MultipartFile file, Integer recipeId, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Recipe recipe = recipeRepository
                .findById(recipeId)
                .orElseThrow(RecipeNotFoundException::new);

        String bucketName = awsProperties.s3().buckets().content().bucketName();
        String objectKey = THUMBNAIL_OBJECT_KEY_PATTERN.formatted(principal.getUserId(), recipeId);

        cnlS3Client.storeFile(file, bucketName, objectKey);

        RecipeImage recipeImage = new RecipeImage()
                .setRecipe(recipe)
                .setImageUrl(cnlS3Client.getFileUrl(bucketName, objectKey));

        recipeImagesRepository.save(recipeImage);
    }
}

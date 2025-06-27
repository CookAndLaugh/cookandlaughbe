package com.vansisto.cookandlaughbe.service.impl;

import com.vansisto.cookandlaughbe.aws.CNLS3Client;
import com.vansisto.cookandlaughbe.config.properties.AwsProperties;
import com.vansisto.cookandlaughbe.entity.Recipe;
import com.vansisto.cookandlaughbe.entity.User;
import com.vansisto.cookandlaughbe.exception.not_found.RecipeNotFoundException;
import com.vansisto.cookandlaughbe.repository.RecipeRepository;
import com.vansisto.cookandlaughbe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.vansisto.cookandlaughbe.misc.Constants.THUMBNAIL_OBJECT_KEY_PATTERN;

@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final CNLS3Client cnlS3Client;
    private final AwsProperties awsProperties;

    @Override
    public void updateRecipeThumbnail(MultipartFile file, Integer recipeId, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        if (!existsById(recipeId)) {
            throw new RecipeNotFoundException(recipeId);
        }

        String bucketName = awsProperties.s3().buckets().content().bucketName();
        String objectKey = THUMBNAIL_OBJECT_KEY_PATTERN.formatted(principal.getUserId(), recipeId);

        cnlS3Client.storeFile(file, bucketName, objectKey);
    }

    @Override
    public Recipe findById(Integer recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));
    }

    @Override
    public boolean existsById(Integer recipeId) {
        return recipeRepository.existsById(recipeId);
    }
}

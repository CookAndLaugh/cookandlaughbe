package com.vansisto.cookandlaughbe.repository;

import com.vansisto.cookandlaughbe.entity.RecipeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeImagesRepository extends JpaRepository<RecipeImage, Integer> {
}

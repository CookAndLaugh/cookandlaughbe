package com.vansisto.cookandlaughbe.repository;

import com.vansisto.cookandlaughbe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}

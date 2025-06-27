package com.vansisto.cookandlaughbe.exception.not_found;

import static com.vansisto.cookandlaughbe.exception.misc.ExceptionMessages.RECIPE_NOT_FOUND_PATTERN;

public class RecipeNotFoundException extends NotFoundException {
    public RecipeNotFoundException(Object... formatArgs) {
        super(RECIPE_NOT_FOUND_PATTERN, formatArgs);
    }
}

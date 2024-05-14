package com.natetrystuff.MealIngredient;

import java.util.List;

import lombok.Data;

@Data
public class MealDTO {
    private Long mealId;
    private String mealName;
    private List<MealIngredientDTO> mealIngredients;

    @Data
    public static class MealIngredientDTO {
        private Long mealIngredientId;
        private String ingredientName;
        private double quantity;
        private String unit;
    }
}
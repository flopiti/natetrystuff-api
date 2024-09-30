package com.natetrystuff.Meal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import com.natetrystuff.MealIngredient.MealIngredient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Data
@Entity
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mealId;

    @OneToMany
    @JoinColumn(name = "meal_id")
    private List<MealIngredient> mealIngredients;

    @Column
    private String mealName;

    public MealDTO getDTO() {
        MealDTO dto = new MealDTO();
        dto.setMealId(this.mealId);
        dto.setMealName(this.mealName);
        dto.setMealIngredients(this.mealIngredients.stream()
            .map(ingredient -> {
                MealDTO.MealIngredientDTO ingredientDTO = new MealDTO.MealIngredientDTO();
                ingredientDTO.setMealIngredientId(ingredient.getMealIngredientId());
                ingredientDTO.setIngredientName(ingredient.getIngredient().getIngredientName());
                ingredientDTO.setQuantity(ingredient.getQuantity());
                ingredientDTO.setUnit(ingredient.getUnit());
                return ingredientDTO;
            }).collect(Collectors.toList()));
        return dto;
    }
}
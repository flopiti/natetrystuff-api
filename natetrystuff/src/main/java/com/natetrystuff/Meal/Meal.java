package com.natetrystuff.Meal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.natetrystuff.Ingredient.Ingredient;
import com.natetrystuff.MealIngredient.MealIngredient;

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

    private String mealName;
}


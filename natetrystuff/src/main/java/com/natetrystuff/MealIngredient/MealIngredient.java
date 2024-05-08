
package com.natetrystuff.MealIngredient;

import lombok.Data;

import com.natetrystuff.Ingredient.Ingredient;
import com.natetrystuff.Meal.Meal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Data
@Table(name = "MEAL_INGREDIENT") // Ensure table name matches database
public class MealIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_ingredient_id") // Ensure column name matches database
    private Long MealIngredientId;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private double quantity;

    private String unit;
}

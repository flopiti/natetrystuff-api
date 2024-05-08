
package com.natetrystuff.MealIngredient;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MealIngredientService {

    private final MealIngredientRepository mealingredientRepository;

    public MealIngredientService(MealIngredientRepository mealingredientRepository) {
        this.mealingredientRepository = mealingredientRepository;
    }

    public List<MealIngredient> listAllMealIngredients() {
        return mealingredientRepository.findAll();
    }

    public MealIngredient getMealIngredientById(Long id) {
        return mealingredientRepository.findById(id).orElse(null);
    }

    public MealIngredient createMealIngredient(MealIngredient mealingredient) {
        return mealingredientRepository.save(mealingredient);
    }

    public MealIngredient updateMealIngredient(Long id, MealIngredient mealingredientDetails) {
        MealIngredient existingMealIngredient = mealingredientRepository.findById(id).orElse(null);
        if (existingMealIngredient != null) {
            existingMealIngredient.setMealIngredientId(mealingredientDetails.getMealIngredientId());
            return mealingredientRepository.save(existingMealIngredient);
        }
        throw new RuntimeException("MealIngredient not found with id " + id);
    }

    public void deleteMealIngredient(Long id) {
        mealingredientRepository.deleteById(id);
    }
}

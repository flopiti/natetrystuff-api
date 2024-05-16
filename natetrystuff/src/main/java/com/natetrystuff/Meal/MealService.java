package com.natetrystuff.Meal;

import org.springframework.stereotype.Service;

import com.natetrystuff.Ingredient.Ingredient;
import com.natetrystuff.Ingredient.IngredientService;
import com.natetrystuff.MealIngredient.MealIngredient;
import com.natetrystuff.MealIngredient.MealIngredientService;
import com.natetrystuff.MealSchedule.MealSchedule;
import com.natetrystuff.MealSchedule.MealScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final MealScheduleService mealScheduleService;
    private final MealIngredientService mealIngredientService;
    private final IngredientService ingredientService;

    public MealService(MealRepository mealRepository, 
    MealScheduleService mealScheduleService, 
    MealIngredientService mealIngredientService, 
    IngredientService ingredientService) {
        this.mealRepository = mealRepository;
        this.mealScheduleService = mealScheduleService;
        this.mealIngredientService = mealIngredientService;
        this.ingredientService = ingredientService;
    }

    public List<Meal> listAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal create(MealDTO meal) {
        Meal newMeal = new Meal();
        newMeal.setMealName(meal.getMealName());
        Meal savedMeal = mealRepository.save(newMeal);
        List<MealIngredient> mealIngredientsList = new ArrayList<>();
        meal.getMealIngredients().forEach(mealIngredient -> {
            Ingredient ingredient;
            Ingredient existingIngredient = ingredientService.findByName(mealIngredient.getIngredientName());
            if (existingIngredient != null) {
                ingredient = existingIngredient;
            } else {
                ingredient = new Ingredient();
                ingredient.setIngredientName(mealIngredient.getIngredientName());
                ingredient = ingredientService.createIngredient(ingredient);
            }
            MealIngredient mealIngredientNew = new MealIngredient();
            mealIngredientNew.setIngredient(ingredient);
            mealIngredientNew.setQuantity(mealIngredient.getQuantity());
            mealIngredientNew.setUnit(mealIngredient.getUnit());   
            mealIngredientNew.setMeal(newMeal);
            MealIngredient savedMealIngredient = mealIngredientService.createMealIngredient(mealIngredientNew);
            mealIngredientsList.add(savedMealIngredient);
        });
        savedMeal.setMealIngredients(mealIngredientsList);
        return savedMeal;
    }


    public Meal updateMeal(Long id, Meal mealDetails) {
        Meal existingMeal = mealRepository.findById(id).orElse(null);
        if (existingMeal != null) {
            existingMeal.setMealName(mealDetails.getMealName());
            return mealRepository.save(existingMeal);
        }
        // throw exception if meal not found
        throw new RuntimeException("Meal not found with id " + id);
    }

    public void delete(Long id) {
        List<MealSchedule> schedules = mealScheduleService.listAllSchedules().stream()
        .filter(meal -> meal.getMeal().getMealId().equals(id)).collect(Collectors.toList());

        mealScheduleService.listAllSchedules().stream()
            .filter(meal -> meal.getMeal().getMealId().equals(id))
            .forEach(meal -> mealScheduleService.deleteSchedule(meal.getScheduleId()));
    
        mealRepository.deleteById(id);
    }
}

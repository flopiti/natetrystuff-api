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

    public List<MealDTO> listAllMeals() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().map(Meal::getDTO).collect(Collectors.toList());
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public MealDTO create(MealDTO meal) {
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
        return savedMeal.getDTO();
    }

    public MealDTO updateMeal(Long id, MealDTO mealDetails) {
        Meal existingMeal = mealRepository.findById(id).orElse(null);
        if (existingMeal != null) {
            existingMeal.setMealName(mealDetails.getMealName());

            List<MealIngredient> existingIngredients = existingMeal.getMealIngredients();
            existingIngredients.forEach(mealIngredient -> mealIngredientService.deleteMealIngredient(mealIngredient.getMealIngredientId()));
            List<MealIngredient> newMealIngredientsList = new ArrayList<>();
            mealDetails.getMealIngredients().forEach(mealIngredient -> {
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
                mealIngredientNew.setMeal(existingMeal);
                MealIngredient savedMealIngredient = mealIngredientService.createMealIngredient(mealIngredientNew);
                newMealIngredientsList.add(savedMealIngredient);
            });
            existingMeal.setMealIngredients(newMealIngredientsList);
            Meal updatedMeal = mealRepository.save(existingMeal);
            return updatedMeal.getDTO(); 
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

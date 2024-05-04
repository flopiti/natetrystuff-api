package com.natetrystuff.Meal;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Meal> listAllMeals() {
        return mealRepository.findAll();
    }

    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal create(Meal meal) {
        return mealRepository.save(meal);
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
        mealRepository.deleteById(id);
    }
}

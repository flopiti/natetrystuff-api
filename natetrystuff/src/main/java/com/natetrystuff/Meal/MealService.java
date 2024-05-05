package com.natetrystuff.Meal;

import org.springframework.stereotype.Service;

import com.natetrystuff.MealSchedule.MealSchedule;
import com.natetrystuff.MealSchedule.MealScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final MealScheduleService mealScheduleService;

    public MealService(MealRepository mealRepository, MealScheduleService mealScheduleService) {
        this.mealRepository = mealRepository;
        this.mealScheduleService = mealScheduleService;
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
        List<MealSchedule> schedules = mealScheduleService.listAllSchedules().stream()
        .filter(meal -> meal.getMeal().getMealId().equals(id)).collect(Collectors.toList());

        mealScheduleService.listAllSchedules().stream()
            .filter(meal -> meal.getMeal().getMealId().equals(id))
            .forEach(meal -> mealScheduleService.deleteSchedule(meal.getScheduleId()));
    
        mealRepository.deleteById(id);
    }
}

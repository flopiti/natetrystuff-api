package com.natetrystuff.MealSchedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.natetrystuff.Meal.Meal;
import com.natetrystuff.Meal.MealRepository;
import com.natetrystuff.MealIngredient.MealIngredient;

@Service
public class MealScheduleService {

    private final MealScheduleRepository mealScheduleRepository;

    private final MealRepository mealRepository;

    public MealScheduleService(MealScheduleRepository mealScheduleRepository, MealRepository mealRepository) {
        this.mealScheduleRepository = mealScheduleRepository;
        this.mealRepository = mealRepository;
    }

    public List<MealSchedule> listAllSchedules() {
        return mealScheduleRepository.findAll();
    }

    public MealSchedule getScheduleById(Long id) {
        return mealScheduleRepository.findById(id).orElse(null);
    }

    public MealSchedule createSchedule(MealSchedule mealSchedule) {
        Meal meal = mealRepository.findById(mealSchedule.getMeal().getMealId())
            .orElseThrow(() -> new RuntimeException("Meal not found with id " + mealSchedule.getMeal().getMealId()));
        mealSchedule.setMeal(meal);

        mealSchedule.setPrepared(false);
        return mealScheduleRepository.save(mealSchedule);
    }

    public MealSchedule updateSchedule(Long id, MealSchedule mealScheduleDetails) {
        MealSchedule existingSchedule = mealScheduleRepository.findById(id).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setScheduledTime(mealScheduleDetails.getScheduledTime());
            existingSchedule.setOccasion(mealScheduleDetails.getOccasion());
            existingSchedule.setPrepared(mealScheduleDetails.isPrepared());
            return mealScheduleRepository.save(existingSchedule);
        }
        throw new RuntimeException("MealSchedule not found with id " + id);
    }

    public void deleteSchedule(Long id) {
        mealScheduleRepository.deleteById(id);
    }

    public List<MealIngredient> getGroceryList(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        List<MealSchedule> schedules = mealScheduleRepository.findByScheduledTimeBetween(startDateTime, endDateTime);
        List<MealIngredient> groceryList = new ArrayList<>();
        for (MealSchedule schedule : schedules) {
            List<MealIngredient> mealIngredients = schedule.getMeal().getMealIngredients();
            groceryList.addAll(mealIngredients);
        }

        for(int i = 0; i < groceryList.size(); i++) {
            for(int j = i + 1; j < groceryList.size(); j++) {

                Long ingredientId1 = groceryList.get(i).getIngredient().getIngredientId();
                Long ingredientId2 = groceryList.get(j).getIngredient().getIngredientId();

                if(ingredientId1 == ingredientId2 && groceryList.get(i).getUnit().equals(groceryList.get(j).getUnit())) {
                    groceryList.get(i).setQuantity(groceryList.get(i).getQuantity() + groceryList.get(j).getQuantity());
                    groceryList.remove(j);
                    j--;
                }
            }
        }
        return groceryList;
    }

    public List<MealSchedule> getMealSchedulesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return mealScheduleRepository.findByScheduledTimeBetween(startDate, endDate);
    }
}

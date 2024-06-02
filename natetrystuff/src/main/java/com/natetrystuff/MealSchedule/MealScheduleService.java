package com.natetrystuff.MealSchedule;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

        // Convert ZonedDateTime to LocalDateTime before setting it
        ZonedDateTime zonedDateTime = mealSchedule.getScheduledTime().atZone(ZoneId.of("America/New_York"));
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        mealSchedule.setScheduledTime(localDateTime);
        
        return mealScheduleRepository.save(mealSchedule);
    }

    public MealSchedule updateSchedule(Long id, MealSchedule mealScheduleDetails) {
        MealSchedule existingSchedule = mealScheduleRepository.findById(id).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setMeal(mealScheduleDetails.getMeal());
            // Convert ZonedDateTime to LocalDateTime before setting it
            ZonedDateTime zonedDateTime = mealScheduleDetails.getScheduledTime().atZone(ZoneId.of("America/New_York"));
            LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
            existingSchedule.setScheduledTime(localDateTime);
            return mealScheduleRepository.save(existingSchedule);
        }
        throw new RuntimeException("MealSchedule not found with id " + id);
    }

    public void deleteSchedule(Long id) {
        mealScheduleRepository.deleteById(id);
    }

    public List<MealIngredient> getGroceryList(LocalDateTime startDate, LocalDateTime endDate) {
        List<MealSchedule> schedules = mealScheduleRepository.findByScheduledTimeBetween(startDate, endDate);
        List<MealIngredient> groceryList = new ArrayList<>();
        for (MealSchedule schedule : schedules) {
            groceryList.addAll(schedule.getMeal().getMealIngredients());
        }
        return groceryList;
    }
}

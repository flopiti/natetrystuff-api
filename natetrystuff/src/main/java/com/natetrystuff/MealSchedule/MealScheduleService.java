package com.natetrystuff.MealSchedule;

import java.util.List;

import org.springframework.stereotype.Service;

import com.natetrystuff.Meal.Meal;
import com.natetrystuff.Meal.MealRepository;

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
        return mealScheduleRepository.save(mealSchedule);
    }

    public MealSchedule updateSchedule(Long id, MealSchedule mealScheduleDetails) {
        MealSchedule existingSchedule = mealScheduleRepository.findById(id).orElse(null);
        if (existingSchedule != null) {
            existingSchedule.setMeal(mealScheduleDetails.getMeal());
            existingSchedule.setScheduledTime(mealScheduleDetails.getScheduledTime());
            return mealScheduleRepository.save(existingSchedule);
        }
        throw new RuntimeException("MealSchedule not found with id " + id);
    }

    public void deleteSchedule(Long id) {
        mealScheduleRepository.deleteById(id);
    }
}

package com.natetrystuff.MealSchedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.natetrystuff.MealIngredient.MealIngredient;

@RestController
@RequestMapping("/meal-schedules")
public class MealScheduleController {

    private final MealScheduleService mealScheduleService;

    public MealScheduleController(MealScheduleService mealScheduleService) {
        this.mealScheduleService = mealScheduleService;
    }

    @GetMapping
    public List<MealSchedule> getAllSchedules() {
        return mealScheduleService.listAllSchedules();
    }

    @GetMapping("/get-groceries")
    public List<MealIngredient> getGroceryList(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return mealScheduleService.getGroceryList(startDate, endDate);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MealSchedule> getScheduleById(@PathVariable Long id) {
        MealSchedule mealSchedule = mealScheduleService.getScheduleById(id);
        if (mealSchedule != null) {
            return ResponseEntity.ok(mealSchedule);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public MealSchedule createSchedule(@RequestBody MealSchedule mealSchedule) {
        return mealScheduleService.createSchedule(mealSchedule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealSchedule> updateSchedule(@PathVariable Long id, @RequestBody MealSchedule mealScheduleDetails) {
        try {
            MealSchedule updatedMealSchedule = mealScheduleService.updateSchedule(id, mealScheduleDetails);
            return ResponseEntity.ok(updatedMealSchedule);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable Long id) {
        try {
            mealScheduleService.deleteSchedule(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}

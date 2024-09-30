package com.natetrystuff.Meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping
    public List<MealDTO> getAllMeals() {
        return mealService.listAllMeals();
    }

    @PostMapping
    public ResponseEntity<MealDTO> addMeal(@RequestBody MealDTO meal) {
        MealDTO newMeal = mealService.create(meal);
        return new ResponseEntity<>(newMeal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealDTO> updateMeal(@PathVariable Long id, @RequestBody MealDTO meal) {
        MealDTO updatedMeal = mealService.updateMeal(id, meal);
        if (updatedMeal != null) {
            return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMeal(@PathVariable Long id) {
        mealService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
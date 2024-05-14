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
        public List<Meal> getAllMeals() {
            return mealService.listAllMeals();
        }
    
        @PostMapping
        public ResponseEntity<Meal> addMeal(@RequestBody MealDTO meal) {
            Meal newMeal = mealService.create(meal);
            return new ResponseEntity<>(newMeal, HttpStatus.CREATED);
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> removeMeal(@PathVariable Long id) {
            mealService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
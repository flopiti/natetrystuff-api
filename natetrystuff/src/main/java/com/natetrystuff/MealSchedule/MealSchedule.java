package com.natetrystuff.MealSchedule;

import java.time.LocalDateTime;

import com.natetrystuff.Meal.Meal;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "meal_schedules")
public class MealSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(name = "meal_id", referencedColumnName = "mealId")
    private Meal meal;

    private LocalDateTime scheduledTime;

    @Column(nullable = true)
    private String occasion;

    @Column(nullable = false)
    private boolean prepared = false; // New field
}

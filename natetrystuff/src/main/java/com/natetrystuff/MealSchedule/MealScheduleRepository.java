package com.natetrystuff.MealSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealScheduleRepository extends JpaRepository<MealSchedule, Long> {
}
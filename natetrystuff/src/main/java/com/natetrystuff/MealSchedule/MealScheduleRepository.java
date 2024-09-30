package com.natetrystuff.MealSchedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealScheduleRepository extends JpaRepository<MealSchedule, Long> {
    List<MealSchedule> findByScheduledTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}

package com.natetrystuff.Day;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DayService {

    private final DayRepository dayRepository;

    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    public List<Day> listAllDays() {
        return dayRepository.findAll();
    }

    public Day getDayById(Long id) {
        return dayRepository.findById(id).orElse(null);
    }

    public Day createDay(Day day) {
        return dayRepository.save(day);
    }

    public Day updateDay(Long id, Day dayDetails) {
        Day existingDay = dayRepository.findById(id).orElse(null);
        if (existingDay != null) {
            existingDay.setDate(dayDetails.getDate());
            existingDay.setInOffice(dayDetails.isInOffice());
            return dayRepository.save(existingDay);
        }
        throw new RuntimeException("Day not found with id " + id);
    }

    public void deleteDay(Long id) {
        dayRepository.deleteById(id);
    }
    
    // New method to get the number of office days in a given month
    public long getNumberOfOfficeDaysInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return dayRepository.findAll().stream()
            .filter(day -> !day.getDate().isBefore(startDate) && !day.getDate().isAfter(endDate) && day.isInOffice())
            .count();
    }
}
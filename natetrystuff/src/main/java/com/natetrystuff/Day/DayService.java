package com.natetrystuff.Day;

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
    
}

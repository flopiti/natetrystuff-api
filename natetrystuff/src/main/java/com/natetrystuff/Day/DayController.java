package com.natetrystuff.Day;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/days")
public class DayController {

    DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping
    public List<Day> getAllDays() {
        return dayService.listAllDays();
    }

    @PostMapping
    public ResponseEntity<Day> addDay(@RequestBody Day day) {
        Day newDay = dayService.createDay(day);
        return new ResponseEntity<>(newDay, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Day> getDayById(@PathVariable Long id) {
        Day day = dayService.getDayById(id);
        if (day != null) {
            return new ResponseEntity<>(day, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}

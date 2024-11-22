package com.natetrystuff.Objective;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/objectives")
public class ObjectiveController {

    private final ObjectiveService objectiveService;

    public ObjectiveController(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @PostMapping
    public ResponseEntity<Objective> addObjective(@RequestBody Objective objective) {
        Objective newObjective = objectiveService.createObjective(objective);
        return new ResponseEntity<>(newObjective, HttpStatus.CREATED);
    }

    @GetMapping("/lowest-id")
    public ResponseEntity<Objective> getObjectiveWithLowestId() {
        Optional<Objective> objective = objectiveService.getObjectiveWithLowestId();
        return objective.map(obj -> new ResponseEntity<>(obj, HttpStatus.OK))
                         .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Void> completeObjective(@PathVariable Long id) {
        boolean completed = objectiveService.completeObjective(id);
        return completed ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeObjective(@PathVariable Long id) {
        objectiveService.deleteObjective(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
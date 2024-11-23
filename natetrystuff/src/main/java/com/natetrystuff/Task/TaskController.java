package com.natetrystuff.Task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/objective/{objectiveId}")
    public ResponseEntity<List<Task>> getTasksForObjective(@PathVariable Long objectiveId) {
        List<Task> tasks = taskService.getTasksForObjective(objectiveId);
        return tasks.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/objective/{objectiveId}")
    public ResponseEntity<Task> addTaskToObjective(@RequestBody Task task, @PathVariable Long objectiveId) {
        Task createdTask = taskService.addTaskToObjective(task, objectiveId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) {
    Task updatedTask = taskService.completeTask(taskId);
    if (updatedTask != null) {
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
}
package com.natetrystuff.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskWithLowestId() {
        return taskRepository.findAll().stream()
                .filter(task -> !task.isFinished())
                .min(Comparator.comparingLong(Task::getTaskId));
    }

    public Task completeTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            Task t = task.get();
            t.setFinished(true);
            taskRepository.save(t);
            return task.get();
        }
        return task.get();
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<Task> getTasksForObjective(Long objectiveId) {
        return taskRepository.findByObjectiveObjectiveId(objectiveId);
    }

    public Task addTaskToObjective(Task task, Long objectiveId) {
        // Assuming the task's objective is already set before adding
        return taskRepository.save(task);
    }
}

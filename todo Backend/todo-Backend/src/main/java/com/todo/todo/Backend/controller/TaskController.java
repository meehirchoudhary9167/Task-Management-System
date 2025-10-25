package com.todo.todo.Backend.controller;

import com.todo.todo.Backend.DTO.TaskDTO;
import com.todo.todo.Backend.model.Task;
import com.todo.todo.Backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks for a user (return DTO)
    @GetMapping
    public List<TaskDTO> getTasks(@RequestParam String username) {
        return taskService.getTasksForUser(username);
    }

    // Add task
    @PostMapping
    public String addTask(@RequestParam String username, @RequestBody Task task) {
        return taskService.addTask(username, task);
    }

    // Update task
    @PutMapping("/{id}")
    public String updateTask(@RequestParam String username, @PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(username, id, task);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public String deleteTask(@RequestParam String username, @PathVariable Long id) {
        return taskService.deleteTask(username, id);
    }

    // Mark task as completed
    @PutMapping("/{id}/complete")
    public String completeTask(@RequestParam String username, @PathVariable Long id) {
        return taskService.markTaskCompleted(username, id);
    }
}

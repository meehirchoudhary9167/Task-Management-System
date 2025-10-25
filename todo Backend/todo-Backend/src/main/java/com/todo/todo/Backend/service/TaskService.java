package com.todo.todo.Backend.service;

import com.todo.todo.Backend.DTO.TaskDTO;
import com.todo.todo.Backend.model.Task;
import com.todo.todo.Backend.model.User;
import com.todo.todo.Backend.repository.TaskRepository;
import com.todo.todo.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all tasks for a user (return DTO)
    // Convert to boolean usage
    public List<TaskDTO> getTasksForUser(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            List<Task> tasks = taskRepository.findByUser(userOpt.get());
            return tasks.stream()
                    .map(task -> new TaskDTO(
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.isCompleted(),  // boolean
                            task.getDueDate()
                    ))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    // Update task
    public String updateTask(String username, Long taskId, Task updatedTask) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent() && taskOpt.get().getUser().getUsername().equals(username)) {
            Task task = taskOpt.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setCompleted(updatedTask.isCompleted()); // boolean
            taskRepository.save(task);
            return "Task updated successfully";
        }
        return "Task not found or unauthorized";
    }

    // Mark task completed
    public String markTaskCompleted(String username, Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent() && taskOpt.get().getUser().getUsername().equals(username)) {
            Task task = taskOpt.get();
            task.setCompleted(true); // boolean
            taskRepository.save(task);
            return "Task marked as completed";
        }
        return "Task not found or unauthorized";
    }
    // Add new task
    public String addTask(String username, Task task) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            task.setUser(userOpt.get());
            taskRepository.save(task);
            return "Task added successfully";
        }
        return "User not found";
    }

    // Delete task
    public String deleteTask(String username, Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent() && taskOpt.get().getUser().getUsername().equals(username)) {
            taskRepository.delete(taskOpt.get());
            return "Task deleted successfully";
        }
        return "Task not found or unauthorized";
    }

}
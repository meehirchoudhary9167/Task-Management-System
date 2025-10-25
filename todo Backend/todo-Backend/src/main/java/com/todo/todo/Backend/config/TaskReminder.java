package com.todo.todo.Backend.config;

import com.todo.todo.Backend.model.Task;
import com.todo.todo.Backend.repository.TaskRepository;
import com.todo.todo.Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class TaskReminder {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmailService emailService;

    private final int REMINDER_MINUTES = 30; // reminder 30 mins before due date
    @Scheduled(fixedRate = 60000)
    public void checkAndSendReminders() {
        System.out.println("TaskReminder running at " + LocalDateTime.now()); // <-- add this
        List<Task> tasks = taskRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Task task : tasks) {
            if (!task.isCompleted() && task.getDueDate() != null) {
                LocalDateTime reminderTime = task.getDueDate().minusMinutes(REMINDER_MINUTES);
                if (now.isAfter(reminderTime) && now.isBefore(task.getDueDate())) {
                    String subject = "Task Reminder: " + task.getTitle();
                    String body = "Your task '" + task.getTitle() + "' is due at " + task.getDueDate();
                    System.out.println("Sending reminder for task: " + task.getTitle()); // <-- add this
                    emailService.sendReminder(task.getUser().getEmail(), subject, body);
                }
            }
        }
    }

}

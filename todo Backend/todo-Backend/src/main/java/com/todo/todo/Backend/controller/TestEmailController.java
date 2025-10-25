package com.todo.todo.Backend.controller;

import com.todo.todo.Backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-test-email")
    public String sendTestEmail() {
        emailService.sendReminder(
                "your-email@example.com", // you can put any email; Ethereal captures it
                "Test Email from Ethereal",
                "This is a test email using Ethereal SMTP"
        );
        return "Test email sent! Check Ethereal Messages page to see it.";
    }
}


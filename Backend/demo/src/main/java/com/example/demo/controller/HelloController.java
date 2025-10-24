package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final MessageRepository repository;

    public HelloController(MessageRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hello")
    public String hello() {
        List<Message> messages = repository.findAll();
        if (messages.isEmpty()) {
            return "No messages in DB!";
        }
        return messages.get(0).getText(); // Return first message
    }
}

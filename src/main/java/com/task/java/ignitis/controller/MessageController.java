package com.task.java.ignitis.controller;

import com.task.java.ignitis.payload.request.MessageCreateRequest;
import com.task.java.ignitis.payload.response.InfoResponse;
import com.task.java.ignitis.payload.response.MessageResponse;
import com.task.java.ignitis.payload.response.MessageStatisticsResponse;
import com.task.java.ignitis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public InfoResponse createMessage(@Valid @RequestBody MessageCreateRequest messageCreateRequest) {
        return messageService.createMessage(messageCreateRequest);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get-messages")
    public List<MessageResponse> getMessages() {
        return messageService.getMessages();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-statistics/{username}")
    public MessageStatisticsResponse getStatistics(@PathVariable("username") String username) {
        return messageService.getUserMessageStatistics(username);
    }
}

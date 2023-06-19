package com.task.java.ignitis.payload.response;

import com.task.java.ignitis.entity.Message;

import java.time.LocalDateTime;

public class MessageResponse {
    private final String sender;

    private final LocalDateTime messageDateTime;

    private final String messageText;

    public MessageResponse(String sender, LocalDateTime messageDateTime, String messageText) {
        this.sender = sender;
        this.messageDateTime = messageDateTime;
        this.messageText = messageText;
    }

    public String getSender() {
        return sender;
    }

    public LocalDateTime getMessageDateTime() {
        return messageDateTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public static MessageResponse fromMessage(Message message) {
        String senderUsername = message.getSender().getUsername();
        LocalDateTime messageDateTime = message.getMessageDateTime();
        String messageText = message.getMessageText();

        return new MessageResponse(senderUsername, messageDateTime, messageText);
    }
}

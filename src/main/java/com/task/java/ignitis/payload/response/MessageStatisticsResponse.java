package com.task.java.ignitis.payload.response;

import java.time.LocalDateTime;

public class MessageStatisticsResponse {
    private String username;
    private Integer messageCount;
    private LocalDateTime firstMessageDateTime;
    private LocalDateTime lastMessageDateTime;
    private Double averageMessageLength;
    private String lastMessageText;

    public MessageStatisticsResponse() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public LocalDateTime getFirstMessageDateTime() {
        return firstMessageDateTime;
    }

    public void setFirstMessageDateTime(LocalDateTime firstMessageDateTime) {
        this.firstMessageDateTime = firstMessageDateTime;
    }

    public LocalDateTime getLastMessageDateTime() {
        return lastMessageDateTime;
    }

    public void setLastMessageDateTime(LocalDateTime lastMessageDateTime) {
        this.lastMessageDateTime = lastMessageDateTime;
    }

    public Double getAverageMessageLength() {
        return averageMessageLength;
    }

    public void setAverageMessageLength(Double averageMessageLength) {
        this.averageMessageLength = averageMessageLength;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public void setLastMessageText(String lastMessageText) {
        this.lastMessageText = lastMessageText;
    }
}

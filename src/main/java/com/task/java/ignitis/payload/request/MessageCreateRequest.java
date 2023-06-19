package com.task.java.ignitis.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageCreateRequest {
    @NotBlank
    @Size(max = 300)
    private String messageText;

    @NotBlank
    private String receiverUsername;

    public String getMessageText() {
        return messageText;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }
}

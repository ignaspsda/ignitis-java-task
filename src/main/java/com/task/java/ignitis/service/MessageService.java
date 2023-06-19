package com.task.java.ignitis.service;

import com.task.java.ignitis.entity.Message;
import com.task.java.ignitis.entity.User;
import com.task.java.ignitis.payload.request.MessageCreateRequest;
import com.task.java.ignitis.payload.response.InfoResponse;
import com.task.java.ignitis.payload.response.MessageResponse;
import com.task.java.ignitis.payload.response.MessageStatisticsResponse;
import com.task.java.ignitis.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public InfoResponse createMessage(MessageCreateRequest messageCreateRequest) {
        String senderUsername = userService.getUsernameFromToken();
        validateSenderAndReceiver(senderUsername, messageCreateRequest.getReceiverUsername());

        String messageText = messageCreateRequest.getMessageText();
        LocalDateTime messageDateTime = LocalDateTime.now();
        User sender = userService.findByUsername(senderUsername);
        User receiver = userService.findByUsername(messageCreateRequest.getReceiverUsername());

        Message message = new Message(messageText, messageDateTime, sender, receiver);
        messageRepository.save(message);

        return new InfoResponse("Message created!");
    }

    public List<MessageResponse> getMessages() {
        String username = userService.getUsernameFromToken();
        User user = userService.findByUsername(username);

        List<Message> receivedMessages = user.getReceivedMessages();

        return receivedMessages.stream()
                .map(MessageResponse::fromMessage)
                .collect(Collectors.toList());
    }

    public MessageStatisticsResponse getUserMessageStatistics(String username) {
        User user = userService.findByUsername(username);
        List<Message> sentMessages = user.getSentMessages();

        MessageStatisticsResponse messageStatistics = new MessageStatisticsResponse();
        messageStatistics.setUsername(username);
        if (!sentMessages.isEmpty()) {
            messageStatistics.setMessageCount(sentMessages.size());
            firstMessageDateForStatistics(messageStatistics, sentMessages);
            lastMessageDateAndTextForStatistics(messageStatistics, sentMessages);
            averageMessageLengthForStatistics(messageStatistics, sentMessages);
        }

        return messageStatistics;
    }

    private void firstMessageDateForStatistics(MessageStatisticsResponse messageStatistics, List<Message> messages) {
        Message firstMessage = Collections.min(messages, Comparator.comparing(Message::getMessageDateTime));
        messageStatistics.setFirstMessageDateTime(firstMessage.getMessageDateTime());
    }

    private void lastMessageDateAndTextForStatistics(MessageStatisticsResponse messageStatistics, List<Message> messages) {
        Message firstMessage = Collections.max(messages, Comparator.comparing(Message::getMessageDateTime));
        messageStatistics.setLastMessageDateTime(firstMessage.getMessageDateTime());
        messageStatistics.setLastMessageText(firstMessage.getMessageText());
    }

    private void averageMessageLengthForStatistics(MessageStatisticsResponse messageStatistics, List<Message> messages) {
        OptionalDouble averageMessageLength = messages.stream()
                .mapToInt(m -> m.getMessageText().length())
                .average();

        if (averageMessageLength.isPresent()) {
            messageStatistics.setAverageMessageLength(averageMessageLength.getAsDouble());
        }
    }

    private void validateSenderAndReceiver(String sender, String receiver) {
        // Check if sender and receiver are the same user
        if (sender.equals(receiver)) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same user");
        }
    }
}

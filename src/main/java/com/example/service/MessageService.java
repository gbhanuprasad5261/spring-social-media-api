package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository,
                          AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {

        if (message == null) {
            return null;
        }

        String messageText = message.getMessageText();
        Integer postedBy = message.getPostedBy();

        if (messageText == null || messageText.trim().length() == 0) {
            return null;
        }

        if (messageText.length() > 255) {
            return null;
        }

        if (postedBy == null) {
            return null;
        }

        if (!accountRepository.existsById(postedBy)) {
            return null;
        }

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll(
                Sort.by(Sort.Direction.ASC, "messageId")
        );
    }

    public Message getMessageById(Integer messageId) {

        if (messageId == null) {
            return null;
        }

        Optional<Message> foundMessage =
                messageRepository.findById(messageId);

        if (foundMessage.isPresent()) {
            return foundMessage.get();
        }

        return null;
    }

    public Integer deleteMessage(Integer messageId) {

        if (messageId == null) {
            return 0;
        }

        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1;
        }

        return 0;
    }

    public Integer updateMessageText(Integer messageId, String newText) {

        if (messageId == null) {
            return 0;
        }

        if (newText == null || newText.trim().length() == 0) {
            return 0;
        }

        if (newText.length() > 255) {
            return 0;
        }

        Optional<Message> foundMessage =
                messageRepository.findById(messageId);

        if (foundMessage.isEmpty()) {
            return 0;
        }

        Message message = foundMessage.get();
        message.setMessageText(newText);
        messageRepository.save(message);

        return 1;
    }

    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}

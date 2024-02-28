package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Message;
import org.example.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }
}

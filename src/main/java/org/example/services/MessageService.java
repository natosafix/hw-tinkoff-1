package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Image;
import org.example.domain.Message;
import org.example.domain.Operation;
import org.example.exceptions.MessageNotFoundException;
import org.example.repositories.ImageRepository;
import org.example.repositories.MessageRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final OperationService operationService;

    public List<Message> getAllMessages() {
        var messages = messageRepository.findAll();

        operationService.logOperation(
                new Operation(
                        String.format("Read messages: %s", messages),
                        Operation.OperationType.READ,
                        LocalDateTime.now())
        );
        return messages;
    }

    @Cacheable(value = "MessageService::getMessageById", key = "#id")
    public Message getMessageById(int id) {
        var message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));

        operationService.logOperation(
                new Operation(
                        String.format("Read message: %s", message),
                        Operation.OperationType.READ,
                        LocalDateTime.now())
        );
        return message;
    }

    @Cacheable(value = "MessageService::getMessageImages", key = "#id")
    public List<Image> getMessageImages(int id) {
        var message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        var images = message.getImages();

        operationService.logOperation(
                new Operation(
                        String.format("Read message images: %s", images),
                        Operation.OperationType.READ,
                        LocalDateTime.now())
        );
        return images;
    }

    public Message sendMessage(Message message) {
        var savedMessage = messageRepository.save(message);

        operationService.logOperation(
                new Operation(
                        String.format("Send message: %s", savedMessage),
                        Operation.OperationType.WRITE,
                        LocalDateTime.now())
        );

        return savedMessage;
    }
}

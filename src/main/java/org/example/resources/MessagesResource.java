package org.example.resources;

import lombok.RequiredArgsConstructor;
import org.example.domain.Image;
import org.example.dtos.ImageDto;
import org.example.dtos.MessageDto;
import org.example.dtos.SendMessageDto;
import org.example.mappers.ImageMapper;
import org.example.mappers.MessageMapper;
import org.example.services.ImageService;
import org.example.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessagesResource {

    private final MessageService messageService;
    private final ImageService imageService;
    private final MessageMapper messageMapper;
    private final ImageMapper imageMapper;

    @GetMapping()
    public List<MessageDto> getMessages() {
        return messageMapper.messagesToMessageDtos(messageService.getAllMessages());
    }

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable int id) {
        return messageMapper.messageToMessageDto(messageService.getMessageById(id));
    }

    @GetMapping("/{id}/images")
    public List<ImageDto> getMessageImages(@PathVariable int id) {
        return imageMapper.imagesToImageDtos(messageService.getMessageImages(id));
    }

    @PostMapping()
    public MessageDto sendMessage(@RequestBody SendMessageDto sendMessageDto) {
        var imagesLinks = sendMessageDto.getImagesLinks();
        List<Image> images = new ArrayList<>();
        if (imagesLinks != null && !imagesLinks.isEmpty()) {
            images = imageService.getImages(imagesLinks);
        }

        var message = messageMapper.sendMessageDtoToMessage(sendMessageDto, images);
        return messageMapper.messageToMessageDto(messageService.sendMessage(message));
    }
}

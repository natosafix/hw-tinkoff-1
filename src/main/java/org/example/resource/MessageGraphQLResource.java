package org.example.resource;

import lombok.RequiredArgsConstructor;
import org.example.dtos.MessageDto;
import org.example.mapper.MessageMapper;
import org.example.services.MessageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageGraphQLResource {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @QueryMapping
    public List<MessageDto> getMessages() {
        return messageMapper.messagesToMessageDtos(messageService.getAllMessages());
    }

    @QueryMapping
    public MessageDto getMessage(@Argument int id) {
        return messageMapper.messageToMessageDto(messageService.getMessageById(id));
    }

    @MutationMapping
    public MessageDto addMessage(@Argument String author, @Argument String content) {
        var message = messageMapper.messageDtoToMessage(new MessageDto(author, content, LocalDateTime.now()));
        return messageMapper.messageToMessageDto(messageService.addMessage(message));
    }
}

package org.example.mappers;

import org.example.domain.Image;
import org.example.domain.Message;
import org.example.dtos.MessageDto;
import org.example.dtos.SendMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageDto messageToMessageDto(Message message);

    Message messageDtoToMessage(MessageDto messageDto);

    List<MessageDto> messagesToMessageDtos(List<Message> messages);

    @Mapping(source = "images", target = "images")
    Message sendMessageDtoToMessage(SendMessageDto messageDto, List<Image> images);
}

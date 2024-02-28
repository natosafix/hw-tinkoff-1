package org.example.mapper;

import org.example.domain.Message;
import org.example.dtos.MessageDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {

    MessageDto messageToMessageDto(Message message);

    Message messageDtoToMessage(MessageDto messageDto);

    List<MessageDto> messagesToMessageDtos(List<Message> messages);
}

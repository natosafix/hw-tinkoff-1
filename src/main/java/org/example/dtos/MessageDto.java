package org.example.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private final String author;
    private final String content;
    private final LocalDateTime lastModifiedDate;
}

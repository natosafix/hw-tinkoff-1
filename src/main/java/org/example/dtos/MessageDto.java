package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {

    private final String author;

    private final String content;

    @JsonAlias("last_modified")
    private final LocalDateTime lastModifiedDate;
}

package org.example.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SendMessageDto {

    private String author;

    private String content;

    @JsonAlias("last_modified")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    private List<String> imagesLinks;
}

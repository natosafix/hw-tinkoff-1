package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int id;
    private String author;
    private String content;
    private LocalDateTime lastModifiedDate;
}

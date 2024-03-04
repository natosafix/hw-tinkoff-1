package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Document
@NoArgsConstructor
public class Operation {

    public Operation(String info, OperationType type, LocalDateTime date) {
        this.info = info;
        this.type = type;
        this.date = date;
    }

    @Id
    private String id;

    private String info;

    private OperationType type;

    private LocalDateTime date;

    public enum OperationType {
        READ,
        WRITE
    }
}



package org.example.dtos;

import lombok.Data;
import org.example.domain.Operation;

import java.time.LocalDateTime;

@Data
public class OperationDto {

    private String info;

    private Operation.OperationType type;

    private LocalDateTime date;
}

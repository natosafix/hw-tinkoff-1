package org.example.resources;

import lombok.RequiredArgsConstructor;
import org.example.domain.Operation;
import org.example.dtos.OperationDto;
import org.example.mappers.OperationMapper;
import org.example.services.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("operations")
public class OperationsResource {

    private final OperationService service;
    private final OperationMapper mapper;

    @GetMapping()
    public List<OperationDto> getOperations(@RequestParam Operation.OperationType type) {
        return mapper.operationsToOperationDtos(service.getByType(type));
    }
}

package org.example.mappers;

import org.example.domain.Operation;
import org.example.dtos.OperationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    public OperationDto operationToOperationDto(Operation operation);

    public Operation operationDtoToOperation(OperationDto operationDto);

    public List<OperationDto> operationsToOperationDtos(List<Operation> operation);
}

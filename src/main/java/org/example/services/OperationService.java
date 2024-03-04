package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.domain.Operation;
import org.example.repositories.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final OperationRepository repository;

    public void logOperation(Operation operation) {
        repository.save(operation);
    }

    public List<Operation> getByType(Operation.OperationType type) {
        return repository.findAllByType(type);
    }
}

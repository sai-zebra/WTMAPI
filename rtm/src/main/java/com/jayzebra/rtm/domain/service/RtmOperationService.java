package com.jayzebra.rtm.domain.service;

import com.jayzebra.rtm.adapter.out.entity.RTMEntity;
import com.jayzebra.rtm.domain.dto.RtmOperationRequestDto;
import com.jayzebra.rtm.domain.port.in.RtmOperationUseCase;
import com.jayzebra.rtm.domain.port.out.RtmOperationRepositoryPort;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;


public class RtmOperationService implements RtmOperationUseCase {

    private final RtmOperationRepositoryPort rtmOperationRepositoryPort;

    public RtmOperationService(RtmOperationRepositoryPort rtmOperationRepositoryPort) {
        this.rtmOperationRepositoryPort = rtmOperationRepositoryPort;
    }

    //    Processes a request to perform a rtm operation --> It creates a new rtmOperationEntity, populates it with data from the request, sets its initial status, and saves it.

    @Override
    public void performRtmOperation(RtmOperationRequestDto requestDto) {
        RTMEntity operation = new RTMEntity();
        operation.setId(UUID.randomUUID().toString());
        // The operation type is mapped from the DTO's enum to the entity's enum.
        operation.setOperation(RTMEntity.RtmOperationType.valueOf(requestDto.getOperation().name()));
        // The payload is copied from the DTO.
        operation.setPayload(requestDto.getPayload());
        // The initial status of the operation is set to ACCEPTED
        operation.setStatus(RTMEntity.OperationStatus.ACCEPTED); // Set initial status
        operation.setCreatedAt(Instant.now());

        rtmOperationRepositoryPort.save(operation);
    }
}

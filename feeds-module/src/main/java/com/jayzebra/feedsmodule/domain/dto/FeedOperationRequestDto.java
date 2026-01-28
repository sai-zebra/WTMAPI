package com.jayzebra.feedsmodule.domain.dto;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Data
// Data Transfer Object (DTO) representing the JSON request body for performing an action on a feed.
public class FeedOperationRequestDto {
    private FeedOperationType operation;
    // A flexible key-value map for data specific to the operation
    private Map<String, Object> payload;

    public FeedOperationType getOperation() {
        return operation;
    }

    public void setOperation(FeedOperationType operation) {
        this.operation = operation;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    // Defines the finite set of all valid operations that can be performed
    public enum FeedOperationType{
        CLAIM, REASSIGN, ACKNOWLEDGE, COMPLETE, ESCALATE
    }
}

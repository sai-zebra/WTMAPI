package com.jayzebra.feedsmodule.adapter.out.entity;

import com.jayzebra.feedsmodule.domain.model.FeedsJsonToMapConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Data
public class FeedOperationEntity {
    @Id
    private String id;

    // Persist the enum by its name (e.g., "CLAIM", "COMPLETE") instead of by its numeric position
    @Enumerated(EnumType.STRING)
    private FeedOperationType operation;

    //This map is converted to a JSON string for database storage using the {@link FeedsJsonToMapConverter}.
    @Convert(converter = FeedsJsonToMapConverter.class)

    // A TEXT column is used to support potentially large JSON payloads.
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> payload;

//  ensures database values remain stable even if the enum's declaration order changes.
    @Enumerated(EnumType.STRING)
    private FeedOperationStatus status;

    // Automatically set on entity creation and is immutable afterward.
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private OffsetDateTime createdAt;


    //Getters and Setters for all attributes of table
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public FeedOperationStatus getStatus() {
        return status;
    }

    public void setStatus(FeedOperationStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }


    //enum class for operation type
    public enum FeedOperationType{
        CLAIM, REASSIGN, ACKNOWLEDGE, COMPLETE, ESCALATE
    }

    //enum class for operation status
    public enum FeedOperationStatus{
        ACCEPTED, PROCESSING, COMPLETED, FAILED
    }
}

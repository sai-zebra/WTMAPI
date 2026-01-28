package com.jayzebra.feedsmodule.adapter.out.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "feed_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,updatable = false) //auto generation of id
    private UUID id;

    @Lob
    @Column(nullable = false) //message should not be null
    private String message;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false) //should not be updatable and null
    private OffsetDateTime createdAt;

    //for mapping feedNotes to feed -- many-to-one relationship
    //one feed can have many feedNotes
    @ManyToOne(fetch = FetchType.LAZY)
    //joining on column feed_id
    @JoinColumn(name = "feed_id",nullable = false)
    private FeedEntity feed;
}

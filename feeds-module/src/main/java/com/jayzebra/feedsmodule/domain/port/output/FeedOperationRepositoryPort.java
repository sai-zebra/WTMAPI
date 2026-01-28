package com.jayzebra.feedsmodule.domain.port.output;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public interface FeedOperationRepositoryPort {
    //to save the operation type in DB
    FeedOperationEntity save(FeedOperationEntity feedOperation);
}

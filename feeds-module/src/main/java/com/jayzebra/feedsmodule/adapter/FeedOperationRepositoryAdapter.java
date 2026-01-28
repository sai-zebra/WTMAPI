package com.jayzebra.feedsmodule.adapter;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.jayzebra.feedsmodule.adapter.out.repository.FeedOperationRepository;
import com.jayzebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;
import org.springframework.stereotype.Repository;

/**
 * Output adapter
 * implementation of all methods in output port and connected with DB
 **/

@Repository
public class FeedOperationRepositoryAdapter implements FeedOperationRepositoryPort {
    private final FeedOperationRepository feedOperationRepository;

    public FeedOperationRepositoryAdapter(FeedOperationRepository feedOperationRepository) {
        this.feedOperationRepository = feedOperationRepository;
    }

    //function to save feedoperation in DB
    @Override
    public FeedOperationEntity save(FeedOperationEntity feedOperation) {
        return feedOperationRepository.save(feedOperation);
    }
}

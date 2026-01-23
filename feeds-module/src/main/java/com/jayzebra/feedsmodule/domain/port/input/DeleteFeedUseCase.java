package com.jayzebra.feedsmodule.domain.port.input;

import java.util.UUID;

/**
 * Input Port for the use case of deleting a feed.
 * Implementation is in Service
 */

@FunctionalInterface
public interface DeleteFeedUseCase {
    void deleteFeed(UUID feedId);
}


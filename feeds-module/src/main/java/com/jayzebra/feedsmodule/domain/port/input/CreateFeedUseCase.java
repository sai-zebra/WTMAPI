package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import org.springframework.modulith.NamedInterface;

//Input Port for the use case of creating a new feed
@NamedInterface
@FunctionalInterface
public interface CreateFeedUseCase {
    void createFeed(FeedCreateRequestDto feedCreateRequestDto);
}

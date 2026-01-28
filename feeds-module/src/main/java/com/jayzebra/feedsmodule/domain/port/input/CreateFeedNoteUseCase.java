package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.model.FeedNote;

import java.util.UUID;

//input port for the useCase of creating feedNote
public interface CreateFeedNoteUseCase {
    FeedNote createFeedNote(UUID feedId, String message);
}

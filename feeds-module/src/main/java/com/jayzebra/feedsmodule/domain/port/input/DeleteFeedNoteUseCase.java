package com.jayzebra.feedsmodule.domain.port.input;

import java.util.UUID;

//input port for deleting feedNote useCase
public interface DeleteFeedNoteUseCase {
    void deleteFeedNote(UUID feedId, UUID noteId);
}

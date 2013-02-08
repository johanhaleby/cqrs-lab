package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class GameAborted extends Event {
    public GameAborted(UUID id) {
        super(id);
    }
}

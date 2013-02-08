package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class GameCreated extends Event {
    public GameCreated(UUID id) {
        super(id);
    }
}

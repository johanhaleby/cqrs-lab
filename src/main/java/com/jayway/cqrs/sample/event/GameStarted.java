package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class GameStarted extends Event {
    public GameStarted(UUID id) {
        super(id);
    }
}

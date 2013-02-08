package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class GameWon extends Event {
    public GameWon(UUID id) {
        super(id);
    }
}

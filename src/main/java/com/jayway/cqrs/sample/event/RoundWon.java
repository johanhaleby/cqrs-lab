package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class RoundWon extends Event {
    public RoundWon(UUID id) {
        super(id);
    }
}

package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class RoundStarted extends Event {
    public RoundStarted(UUID id) {
        super(id);
    }
}

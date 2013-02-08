package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class ChoiceMade extends Event {
    public ChoiceMade(UUID id) {
        super(id);
    }
}

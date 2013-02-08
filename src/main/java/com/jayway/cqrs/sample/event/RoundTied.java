package com.jayway.cqrs.sample.event;

import java.util.UUID;

public class RoundTied extends Event {
    public RoundTied(UUID id) {
        super(id);
    }
}

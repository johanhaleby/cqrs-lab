package com.jayway.cqrs.sample.command;

import java.util.UUID;

public abstract class Command {

    public final UUID id;

    protected Command(UUID id) {
        this.id = id;
    }


}

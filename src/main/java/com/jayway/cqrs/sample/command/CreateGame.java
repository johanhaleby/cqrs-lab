package com.jayway.cqrs.sample.command;

import java.util.UUID;

public class CreateGame extends Command {

    public CreateGame(UUID id) {
        super(id);
    }
}

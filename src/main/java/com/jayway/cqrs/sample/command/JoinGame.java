package com.jayway.cqrs.sample.command;

import java.util.UUID;

public class JoinGame extends Command {

    public JoinGame(UUID id) {
        super(id);
    }
}

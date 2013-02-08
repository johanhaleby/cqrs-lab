package com.jayway.cqrs.sample.command;

import com.jayway.cqrs.sample.domain.PlayerId;

import java.util.UUID;

public class JoinGame extends Command {

    public final PlayerId playerId;

    public JoinGame(GameId id, PlayerId playerId) {
        super(id.id);
        this.playerId = playerId;
    }
}

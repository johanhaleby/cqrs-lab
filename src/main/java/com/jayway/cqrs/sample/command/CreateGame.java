package com.jayway.cqrs.sample.command;

import com.jayway.cqrs.sample.domain.PlayerId;

import java.util.UUID;

public class CreateGame extends Command {

    public final PlayerId playerId;

    public CreateGame(GameId gameId, PlayerId playerId) {
        super(gameId.id);
        this.playerId = playerId;
    }
}

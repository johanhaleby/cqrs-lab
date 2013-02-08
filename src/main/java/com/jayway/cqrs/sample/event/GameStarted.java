package com.jayway.cqrs.sample.event;

import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;

public class GameStarted extends Event {

    public final PlayerId playerId;

    public GameStarted(GameId gameId, PlayerId playerId) {
        super(gameId.id);
        this.playerId = playerId;
    }

}

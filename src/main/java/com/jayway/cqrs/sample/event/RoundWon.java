package com.jayway.cqrs.sample.event;

import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;

public class RoundWon extends Event {
    public final PlayerId playerId;

    public RoundWon(GameId gameId, PlayerId playerId) {
        super(gameId.id);
        this.playerId = playerId;
    }
}

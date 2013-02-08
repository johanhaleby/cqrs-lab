package com.jayway.cqrs.sample.event;

import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;

public class RoundStarted extends Event {
    private final GameId gameId;
    private final PlayerId playerId1;
    private final PlayerId playerId2;

    public RoundStarted(GameId gameId, PlayerId playerId1, PlayerId playerId2) {
        super(gameId.id);
        this.gameId = gameId;
        this.playerId1 = playerId1;
        this.playerId2 = playerId2;
    }
}

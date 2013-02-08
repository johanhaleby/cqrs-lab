package com.jayway.cqrs.sample.event;

import com.jayway.cqrs.sample.command.Choice;
import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;

public class ChoiceMade extends Event {
    public final PlayerId playerId;
    public final Choice choice;

    public ChoiceMade(GameId gameId, PlayerId playerId, Choice choice) {
        super(gameId.id);
        this.playerId = playerId;
        this.choice = choice;
    }
}

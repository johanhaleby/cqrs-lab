package com.jayway.cqrs.sample.command;

import com.jayway.cqrs.sample.domain.PlayerId;

public class MakeChoice extends Command {
    public final PlayerId playerId;
    public final Choice choice;

    public MakeChoice(GameId gameId, PlayerId playerId, Choice choice) {
        super(gameId.id);
        this.playerId = playerId;
        this.choice = choice;
    }
}

package com.jayway.cqrs.sample.event;

import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;

public class RoundTied extends Event {
    public final PlayerId player1;
    public final PlayerId player2;

    public RoundTied(GameId id, PlayerId player1, PlayerId player2) {
        super(id.id);
        this.player1 = player1;
        this.player2 = player2;
    }
}

package com.jayway.cqrs.sample.domain;

public class RoundWonOutcome extends RoundOutcome {

    protected RoundWonOutcome(PlayerId winner) {
        super(winner, true);
    }
}

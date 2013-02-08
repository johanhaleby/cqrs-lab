package com.jayway.cqrs.sample.domain;

public abstract class RoundOutcome {

    private final PlayerId winner;
    private final boolean completed;

    protected RoundOutcome(PlayerId winner, boolean completed) {
        this.winner = winner;
        this.completed = completed;
    }

    public boolean isTied() {
        return isCompleted() && winner == null;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean hasWinner() {
        return completed && winner != null;
    }

    public PlayerId winner() {
        return winner;
    }
}

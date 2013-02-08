package com.jayway.cqrs.sample.domain;

import com.jayway.cqrs.sample.command.Choice;

import static com.jayway.cqrs.sample.command.Choice.*;

public class Round {

    private RoundChoice previousChoice = null;

    public RoundOutcome play(PlayerId playerId, Choice choice) {
        if(previousChoice != null) {
            return determineWinner(previousChoice, new RoundChoice(playerId, choice));
        }
       return new RoundOngoingOutcome();
    }

    public void recordChoiceMade(PlayerId playerId, Choice choice) {
        this.previousChoice = new RoundChoice(playerId, choice);
    }

    // TODO Should probably be extracted to own class
    private RoundOutcome determineWinner(RoundChoice one, RoundChoice two) {
        final PlayerId winner;

        if(one.choice == ROCK  && two.choice == PAPER) winner = two.playerId;
        else if(one.choice == SCISSORS  && two.choice == PAPER)  winner = one.playerId;

        else if(one.choice == ROCK  && two.choice == SCISSORS) winner = one.playerId;
        else if(one.choice == PAPER  && two.choice == SCISSORS)  winner = two.playerId;

        else if(one.choice == SCISSORS  && two.choice == ROCK) winner = two.playerId;
        else if(one.choice == PAPER  && two.choice == ROCK)  winner = one.playerId;
        else winner = null;

        return winner == null ? new RoundTiedOutcome() : new RoundWonOutcome(winner);
    }

    private static class RoundChoice {
        final PlayerId playerId;
        final Choice choice;


        private RoundChoice(PlayerId playerId, Choice choice) {
            this.playerId = playerId;
            this.choice = choice;
        }
    }
}

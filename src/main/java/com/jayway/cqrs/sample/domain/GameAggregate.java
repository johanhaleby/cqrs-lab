package com.jayway.cqrs.sample.domain;

import com.jayway.cqrs.sample.command.CreateGame;
import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.command.JoinGame;
import com.jayway.cqrs.sample.command.MakeChoice;
import com.jayway.cqrs.sample.event.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameAggregate {

    private State state = State.UNDEFINED;
    private PlayerId playerOne;
    private PlayerId playerTwo;

    private int numberOfRoundWinsPlayer1 = 0;
    private int numberOfRoundWinsPlayer2 = 0;

    private Round currentRound;

    @CommandHandler
    public List<? extends Event> handle(CreateGame c) {
        //    	TODO: command validation
        return Arrays.asList(new GameCreated(new GameId(c.id), c.playerId));
    }

    @CommandHandler
    public List<? extends Event> handle(JoinGame c) {
        //    	TODO: command validation
        final GameId gameId = new GameId(c.id);
        return Arrays.asList(new GameStarted(gameId, c.playerId), new RoundStarted(gameId, playerOne, playerTwo));
    }

    @CommandHandler
    public List<? extends Event> handle(MakeChoice c) {
        final List<Event> events = new ArrayList<Event>();
        final RoundOutcome outcome = currentRound.play(c.playerId, c.choice);
        final GameId gameId = new GameId(c.id);
        events.add(new ChoiceMade(gameId, c.playerId, c.choice));
        if(outcome.isCompleted()) {
            if(outcome.isTied()) {
                events.add(new RoundTied(gameId, playerOne, playerTwo));
            } else {
                events.add(new RoundWon(gameId, outcome.winner()));

                if(playerOne.equals(outcome.winner()) && ++numberOfRoundWinsPlayer1 == 3) {
                    events.add(new GameWon(gameId, playerOne));
                } else if(playerTwo.equals(outcome.winner()) && ++numberOfRoundWinsPlayer2 == 3) {
                    events.add(new GameWon(gameId, playerTwo));
                }
            }
        }
        return events;
    }

    @EventHandler
    public void handle(GameCreated e) {
        this.playerOne = e.playerId;
        this.state = State.CREATED;
    }

    @EventHandler
    public void handle(GameStarted e) {
        this.state = State.STARTED;
        this.playerTwo = e.playerId;
    }

    @EventHandler
    public void handle(RoundStarted e) {
        createNewRound();
    }

    @EventHandler
    public void handle(RoundTied e) {
        createNewRound();
    }

    @EventHandler
    public void handle(RoundWon e) {
        createNewRound();
        if(e.playerId.equals(playerOne)) {
            numberOfRoundWinsPlayer1++;
        } else if(e.playerId.equals(playerTwo)) {
            numberOfRoundWinsPlayer2++;
        }
    }

    private void createNewRound() {
        currentRound = new Round();
    }

    @EventHandler
    public void handle(ChoiceMade e) {
        currentRound.recordChoiceMade(e.playerId, e.choice);
    }

    @EventHandler
    public void handle(GameWon e) {
        this.state = State.FINISHED;
    }


    private enum State {
        UNDEFINED, CREATED, STARTED, ABORTED, FINISHED
    }
}

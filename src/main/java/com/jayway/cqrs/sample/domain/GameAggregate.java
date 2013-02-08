package com.jayway.cqrs.sample.domain;

import com.jayway.cqrs.sample.command.CreateGame;
import com.jayway.cqrs.sample.command.JoinGame;
import com.jayway.cqrs.sample.command.MakeChoice;
import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.event.GameCreated;
import com.jayway.cqrs.sample.event.GameStarted;

import java.util.Arrays;
import java.util.List;

public class GameAggregate {

    private State state = State.UNDEFINED;


    @CommandHandler
    public List<? extends Event> handle(CreateGame c) {
    	// TODO: command validation
    	return Arrays.asList(new GameCreated(c.id));
    }

    @CommandHandler
    public List<? extends Event> handle(JoinGame c) {
    	// TODO: command validation
    	return Arrays.asList(new GameStarted(c.id));
    }

    @CommandHandler
    public List<? extends Event> handle(MakeChoice c) {
//    	TODO: command validation
//    	return Arrays.asList(new GameStarted(c.id));
        return null;
    }


    @EventHandler
    public void handle(GameCreated e) {
        this.state = State.CREATED;
    }

    @EventHandler
    public void handle(GameStarted e) {
        this.state = State.STARTED;
    }



    private enum State {
        UNDEFINED, CREATED, STARTED, ABORTED, FINISHED

    }


}

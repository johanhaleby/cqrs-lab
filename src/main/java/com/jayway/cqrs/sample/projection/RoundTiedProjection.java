package com.jayway.cqrs.sample.projection;

import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.event.RoundTied;

public class RoundTiedProjection implements EventListener {

    public void receive(Iterable<Event> events) {
        for (Event event : events) {
            if(event instanceof RoundTied) {
                RoundTied e = (RoundTied) event;
                System.out.println("Round tied between "+e.player1 + " and "+e.player2);
            }
        }
    }
}

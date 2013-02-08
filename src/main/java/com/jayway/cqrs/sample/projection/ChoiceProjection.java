package com.jayway.cqrs.sample.projection;

import com.jayway.cqrs.sample.event.ChoiceMade;
import com.jayway.cqrs.sample.event.Event;

public class ChoiceProjection implements EventListener {

    public void receive(Iterable<Event> events) {
        for (Event event : events) {
            if(event instanceof ChoiceMade) {
                ChoiceMade e = (ChoiceMade) event;
                System.out.println(e.playerId + " chose " + e.choice);
            }
        }
    }
}

package com.jayway.cqrs.sample.projection;

import com.jayway.cqrs.sample.event.Event;

public interface EventListener {
    void receive(Iterable<Event> events);
}

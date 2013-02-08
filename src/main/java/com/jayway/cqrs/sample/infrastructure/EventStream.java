package com.jayway.cqrs.sample.infrastructure;

import com.jayway.cqrs.sample.event.Event;

public interface EventStream extends Iterable<Event> {
    long version();
}
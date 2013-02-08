package com.jayway.cqrs.sample.infrastructure;

import com.jayway.cqrs.sample.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class EventStreamImpl implements EventStream {

    private final long version;
    private final List<Event> events;

    public EventStreamImpl(long version, List<Event> events) {
        this.version = version;
        this.events = Collections.unmodifiableList(new ArrayList<Event>(events));
    }

    public long version() {
        return version;
    }

    public Iterator<Event> iterator() {
        return events.iterator();
    }
}

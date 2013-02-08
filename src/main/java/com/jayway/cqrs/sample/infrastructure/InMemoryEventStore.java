package com.jayway.cqrs.sample.infrastructure;

import com.jayway.cqrs.sample.event.Event;

import java.util.*;

public class InMemoryEventStore implements EventStore {

    private Map<UUID, EventStream> eventStreams = new HashMap<UUID, EventStream>();

    public synchronized EventStream loadEventStream(UUID streamId) {
        EventStream eventStream = eventStreams.get(streamId);
        if(eventStream == null) {
            eventStream = new EventStreamImpl(1, Collections.<Event>emptyList());
            eventStreams.put(streamId, eventStream);
        }
        return eventStream;
    }

    public synchronized void store(UUID streamId, long version, List<Event> events) {
        final EventStream eventStream = loadEventStream(streamId);

        if(eventStream.version() != version) {
            throw new IllegalStateException("Version conflict");
        }

        List<Event> merged = new ArrayList<Event>();
        for (Event event : eventStream) {
            merged.add(event);
        }

        merged.addAll(events);

        eventStreams.put(streamId, new EventStreamImpl(++version, merged));
    }
}

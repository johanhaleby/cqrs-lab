package com.jayway.cqrs.sample.infrastructure;

import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.projection.EventListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DelegatingEventStore implements EventStore {

    private final EventStore eventStore;
    private final List<EventListener> eventListeners;

    public DelegatingEventStore(EventStore eventStore, EventListener... eventListeners) {
        this.eventStore = eventStore;
        this.eventListeners = Collections.unmodifiableList(Arrays.asList(eventListeners));
    }

    public EventStream loadEventStream(UUID streamId) {
        return eventStore.loadEventStream(streamId);
    }

    public synchronized void store(UUID streamId, long version, List<Event> events) {
        eventStore.store(streamId, version, events);

        final EventStream eventStream = loadEventStream(streamId);
        for (EventListener eventListener : eventListeners) {
            eventListener.receive(eventStream);
        }
    }
}

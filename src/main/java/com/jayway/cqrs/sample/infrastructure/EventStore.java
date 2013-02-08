package com.jayway.cqrs.sample.infrastructure;

import com.jayway.cqrs.sample.event.Event;

import java.util.List;
import java.util.UUID;

public interface EventStore {
    EventStream loadEventStream(UUID streamId);
	void store(UUID streamId, long version, List<Event> events);
}
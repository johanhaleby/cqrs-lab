package com.jayway.cqrs.sample.application;

import com.jayway.cqrs.sample.command.Command;
import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.handler.MessageHandler;
import com.jayway.cqrs.sample.infrastructure.EventStore;
import com.jayway.cqrs.sample.infrastructure.EventStream;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {

    private final EventStore eventStore;
    private final Class<?> aggregateType;
    private final MessageHandler<Command> commandHandler;
    private final MessageHandler<Event> eventHandler;

    public ApplicationServiceImpl(EventStore eventStore, Class<?> aggregateType) {
        this.eventStore = eventStore;
		this.aggregateType = aggregateType;
		this.commandHandler = new MessageHandler<Command>();
		this.eventHandler = new MessageHandler<Event>();
	}
	
	public void handle(Command command) throws Exception {
        // TODO: load eventStream
        final EventStream eventStream = eventStore.loadEventStream(command.id);

		// TODO: instantiate blank aggregate
        final Object aggregate = aggregateType.newInstance();

		// TODO: replay eventStream
        for (Event event : eventStream) {
            eventHandler.handle(aggregate, event);
        }

        // TODO: execute command
        final List<Event> newEvents = commandHandler.handle(aggregate, command);

		// TODO: store eventStream
        eventStore.store(command.id, eventStream.version(), newEvents);
    }
}
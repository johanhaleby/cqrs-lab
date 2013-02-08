package com.jayway.cqrs.sample;

import com.jayway.cqrs.sample.application.ApplicationService;
import com.jayway.cqrs.sample.application.ApplicationServiceImpl;
import com.jayway.cqrs.sample.command.CreateGame;
import com.jayway.cqrs.sample.domain.GameAggregate;
import com.jayway.cqrs.sample.event.GameCreated;
import com.jayway.cqrs.sample.infrastructure.EventStore;
import com.jayway.cqrs.sample.infrastructure.InMemoryEventStore;
import com.jayway.cqrs.sample.infrastructure.EventStream;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.fest.assertions.Assertions.assertThat;

public class ApplicationServiceTest {

    @Test public void
    _() throws Exception {
        // Given
        final EventStore eventStore = new InMemoryEventStore();
        final ApplicationService applicationService = new ApplicationServiceImpl(eventStore, GameAggregate.class);
        final UUID id = UUID.randomUUID();

        // When
        applicationService.handle(new CreateGame(id));

        // Then
        final EventStream eventStream = eventStore.loadEventStream(id);

        assertThat(eventStream).containsOnly(new GameCreated(id));
        assertThat(eventStream.version()).isEqualTo(2);
    }

    @Test public void
    illegal_state_exception_is_thrown_when_race_condition() throws Exception {
        // Given
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        final EventStore eventStore = new InMemoryEventStore();
        final ApplicationService applicationService = new ApplicationServiceImpl(eventStore, GameAggregate.class);
        final UUID id = UUID.randomUUID();

        final AtomicBoolean exceptionCaught = new AtomicBoolean(false);

        // When
        new Thread(new Runnable() {
            public void run() {
                try {
                    cyclicBarrier.await();
                    try {
                    applicationService.handle(new CreateGame(id));
                    } catch (IllegalStateException e) {
                        exceptionCaught.set(true);
                    }
                } catch (Exception e) {
                    System.out.println("OOh this is bad");
                }
            }
        }).start();

        cyclicBarrier.await();
        try {
            applicationService.handle(new CreateGame(id));
        } catch (IllegalStateException e) {
            exceptionCaught.set(true);
        }


        // Then
        assertThat(exceptionCaught.get()).isTrue();
    }


}

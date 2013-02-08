package com.jayway.cqrs.sample;

import com.jayway.cqrs.sample.command.CreateGame;
import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.GameAggregate;
import com.jayway.cqrs.sample.domain.PlayerId;
import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.event.GameCreated;
import com.jayway.cqrs.sample.handler.MessageHandler;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;

public class MessageHandlerTest {


    @Test
    public void
    _() throws Exception {
        // Given
        final GameAggregate gameAggregate = new GameAggregate();
        final MessageHandler<CreateGame> messageHandler = new MessageHandler<CreateGame>();
        final UUID id = UUID.randomUUID();
        final GameId gameId = new GameId(id);
        final PlayerId playerId = new PlayerId(UUID.randomUUID());

        // When
        final List<Event> events = messageHandler.handle(gameAggregate, new CreateGame(gameId, playerId));

        // Then
        assertThat(events).containsExactly(new GameCreated(gameId, playerId));
    }
}

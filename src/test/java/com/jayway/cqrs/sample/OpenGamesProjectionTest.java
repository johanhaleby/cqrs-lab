package com.jayway.cqrs.sample;

import com.jayway.cqrs.sample.application.ApplicationService;
import com.jayway.cqrs.sample.application.ApplicationServiceImpl;
import com.jayway.cqrs.sample.command.CreateGame;
import com.jayway.cqrs.sample.domain.GameAggregate;
import com.jayway.cqrs.sample.infrastructure.DelegatingEventStore;
import com.jayway.cqrs.sample.infrastructure.EventStore;
import com.jayway.cqrs.sample.infrastructure.InMemoryEventStore;
import com.jayway.cqrs.sample.projection.OpenGamesProjection;
import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;


public class OpenGamesProjectionTest {

    @Test public void
    open_games_projection_is_records_new_open_game_when_a_new_game_is_created() throws Exception {
        // Given
        final OpenGamesProjection openGamesProjection = new OpenGamesProjection();
        final EventStore eventStore = new DelegatingEventStore(new InMemoryEventStore(), openGamesProjection);

        final ApplicationService applicationService = new ApplicationServiceImpl(eventStore, GameAggregate.class);
        final UUID id = UUID.randomUUID();

        // When
        applicationService.handle(new CreateGame(id));

        // Then
        assertThat(openGamesProjection.getOpenGames()).containsOnly(new OpenGamesProjection.OpenGame(id));
    }
}

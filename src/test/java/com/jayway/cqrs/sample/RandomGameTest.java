package com.jayway.cqrs.sample;

import com.jayway.cqrs.sample.application.ApplicationService;
import com.jayway.cqrs.sample.application.ApplicationServiceImpl;
import com.jayway.cqrs.sample.command.*;
import com.jayway.cqrs.sample.domain.GameAggregate;
import com.jayway.cqrs.sample.domain.PlayerId;
import com.jayway.cqrs.sample.infrastructure.DelegatingEventStore;
import com.jayway.cqrs.sample.infrastructure.EventStore;
import com.jayway.cqrs.sample.infrastructure.InMemoryEventStore;
import com.jayway.cqrs.sample.projection.GamesWonProjection;
import com.jayway.cqrs.sample.projection.OpenGamesProjection;
import org.junit.Test;

import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;

public class RandomGameTest {

    @Test public void
    _() throws Exception {
        final OpenGamesProjection openGamesProjection = new OpenGamesProjection();
        final GamesWonProjection gamesWonProjection = new GamesWonProjection();
        final EventStore eventStore = new DelegatingEventStore(new InMemoryEventStore(), openGamesProjection, gamesWonProjection);

        final ApplicationService applicationService = new ApplicationServiceImpl(eventStore, GameAggregate.class);

        final GameId gameId = new GameId(UUID.randomUUID());
        final PlayerId playerOne = new PlayerId(UUID.randomUUID());
        final PlayerId playerTwo = new PlayerId(UUID.randomUUID());

        // When
        applicationService.handle(new CreateGame(gameId, playerOne));
        applicationService.handle(new JoinGame(gameId, playerTwo));

        applicationService.handle(new MakeChoice(gameId, playerOne, Choice.PAPER));
        applicationService.handle(new MakeChoice(gameId, playerTwo, Choice.ROCK));
        applicationService.handle(new MakeChoice(gameId, playerOne, Choice.PAPER));
        applicationService.handle(new MakeChoice(gameId, playerTwo, Choice.ROCK));
        applicationService.handle(new MakeChoice(gameId, playerOne, Choice.PAPER));
        applicationService.handle(new MakeChoice(gameId, playerTwo, Choice.ROCK));

        // Then
        final Set<GamesWonProjection.WonGame> wonGames = gamesWonProjection.getWonGames();
        final GamesWonProjection.WonGame wonGame = wonGames.iterator().next();
        assertThat(wonGame.getPlayerId()).isEqualTo(playerOne);
    }
}

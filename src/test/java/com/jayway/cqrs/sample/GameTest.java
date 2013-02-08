package com.jayway.cqrs.sample;

import com.jayway.cqrs.sample.application.ApplicationService;
import com.jayway.cqrs.sample.application.ApplicationServiceImpl;
import com.jayway.cqrs.sample.command.*;
import com.jayway.cqrs.sample.domain.GameAggregate;
import com.jayway.cqrs.sample.domain.PlayerId;
import com.jayway.cqrs.sample.infrastructure.DelegatingEventStore;
import com.jayway.cqrs.sample.infrastructure.EventStore;
import com.jayway.cqrs.sample.infrastructure.InMemoryEventStore;
import com.jayway.cqrs.sample.projection.*;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class GameTest {

    @Test public void
    random_game() throws Exception {
         // Given
        final OpenGamesProjection openGamesProjection = new OpenGamesProjection();
        final GamesWonProjection gamesWonProjection = new GamesWonProjection();
        final RoundWonProjection roundWonProjection = new RoundWonProjection();
        final RoundTiedProjection roundTiedProjection = new RoundTiedProjection();
        final ChoiceProjection choiceProjection = new ChoiceProjection();
        final EventStore eventStore = new DelegatingEventStore(new InMemoryEventStore(), openGamesProjection, gamesWonProjection,
                roundWonProjection, roundTiedProjection, choiceProjection);

        final ApplicationService applicationService = new ApplicationServiceImpl(eventStore, GameAggregate.class);

        final GameId gameId = new GameId(UUID.randomUUID());
        final PlayerId playerOne = new PlayerId(UUID.randomUUID());
        final PlayerId playerTwo = new PlayerId(UUID.randomUUID());

        // When
        applicationService.handle(new CreateGame(gameId, playerOne));
        applicationService.handle(new JoinGame(gameId, playerTwo));

        while (gamesWonProjection.numberOfWonGames() < 1) {
            applicationService.handle(new MakeChoice(gameId, playerOne, randomChoice()));
            applicationService.handle(new MakeChoice(gameId, playerTwo, randomChoice()));
        }

        System.out.println("--------------- WINNER: " + gamesWonProjection.findWinnerOfGame(gameId)+ " ---------------");
    }

    public Choice randomChoice() {
        final Random random = new Random();
        return Choice.values()[random.nextInt(3)];
    }
}

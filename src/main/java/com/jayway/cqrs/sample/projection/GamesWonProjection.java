package com.jayway.cqrs.sample.projection;

import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;
import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.event.GameWon;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class GamesWonProjection implements EventListener {

    final Set<WonGame> wonGames = new LinkedHashSet<WonGame>();

    public void receive(Iterable<Event> events) {
        for (Event event : events) {
            if(event instanceof GameWon) {
                GameWon e = (GameWon) event;
                System.out.println("Game won by "+e.playerId);
                wonGames.add(new WonGame(new GameId(e.id), e.playerId));
            }
        }
    }

    public Set<WonGame> getWonGames() {
        return Collections.unmodifiableSet(wonGames);
    }

    public int numberOfWonGames() {
        return wonGames.size();
    }

    public PlayerId findWinnerOfGame(GameId gameId) {
        for (WonGame wonGame : wonGames) {
            if(wonGame.getGameId().equals(gameId)) {
                return wonGame.getPlayerId();
            }
        }
        return null;
    }

    public static class WonGame {
        private final GameId gameId;
        private final PlayerId playerId;

        public WonGame(GameId id, PlayerId playerId) {
            this.playerId = playerId;
            this.gameId = id;
        }

        public GameId getGameId() {
            return gameId;
        }

        public PlayerId getPlayerId() {
            return playerId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WonGame wonGame = (WonGame) o;

            if (gameId != null ? !gameId.equals(wonGame.gameId) : wonGame.gameId != null) return false;
            if (playerId != null ? !playerId.equals(wonGame.playerId) : wonGame.playerId != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = gameId != null ? gameId.hashCode() : 0;
            result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("WonGame");
            sb.append("{gameId=").append(gameId);
            sb.append(", playerId=").append(playerId);
            sb.append('}');
            return sb.toString();
        }
    }

}

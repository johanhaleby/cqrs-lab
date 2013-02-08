package com.jayway.cqrs.sample.projection;

import com.jayway.cqrs.sample.command.GameId;
import com.jayway.cqrs.sample.domain.PlayerId;
import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.event.GameWon;
import com.jayway.cqrs.sample.event.RoundWon;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class RoundWonProjection implements EventListener {

    final Set<WonRound> wonRounds = new LinkedHashSet<WonRound>();

    public void receive(Iterable<Event> events) {
        for (Event event : events) {
            if(event instanceof RoundWon) {
                RoundWon e = (RoundWon) event;
                System.out.println("Round won by "+e.playerId);
                wonRounds.add(new WonRound(new GameId(e.id), e.playerId));
            }
        }
    }

    public Set<WonRound> getWonRounds() {
        return Collections.unmodifiableSet(wonRounds);
    }

    public static class WonRound {
        private final GameId gameId;
        private final PlayerId playerId;

        public WonRound(GameId id, PlayerId playerId) {
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

            WonRound wonRound = (WonRound) o;

            if (gameId != null ? !gameId.equals(wonRound.gameId) : wonRound.gameId != null) return false;
            if (playerId != null ? !playerId.equals(wonRound.playerId) : wonRound.playerId != null) return false;

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

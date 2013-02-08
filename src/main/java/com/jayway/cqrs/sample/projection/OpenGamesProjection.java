package com.jayway.cqrs.sample.projection;

import com.jayway.cqrs.sample.event.Event;
import com.jayway.cqrs.sample.event.GameAborted;
import com.jayway.cqrs.sample.event.GameCreated;
import com.jayway.cqrs.sample.event.GameWon;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class OpenGamesProjection implements EventListener {

    final Set<OpenGame> openGames = new LinkedHashSet<OpenGame>();

    public void receive(Iterable<Event> events) {
        for (Event event : events) {
            if(event instanceof GameCreated) {
                openGames.add(new OpenGame(event.id));
            } else if(event instanceof GameAborted || event instanceof GameWon) {
                openGames.remove(new OpenGame(event.id));
            }
        }
    }

    private static class OpenGame {
        private final UUID id;

        private OpenGame(UUID id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OpenGame openGame = (OpenGame) o;

            if (id != null ? !id.equals(openGame.id) : openGame.id != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

}

package com.jayway.cqrs.sample.command;

import java.util.UUID;

public class GameId {
    public final UUID id;

    public GameId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameId gameId = (GameId) o;

        if (id != null ? !id.equals(gameId.id) : gameId.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

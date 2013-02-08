package com.jayway.cqrs.sample.domain;

import java.util.UUID;

public class PlayerId {
    public final UUID id;

    public PlayerId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PlayerId");
        sb.append("{id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

package com.jayway.cqrs.sample.command;

import java.util.UUID;

public class MakeChoice extends Command {

    public MakeChoice(UUID id) {
        super(id);
    }


}

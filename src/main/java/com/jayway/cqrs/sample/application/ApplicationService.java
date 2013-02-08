package com.jayway.cqrs.sample.application;

import com.jayway.cqrs.sample.command.Command;

public interface ApplicationService {
    void handle(Command command) throws Exception;
}

package com.jayway.cqrs.sample.domain;

public class RoundTiedOutcome extends RoundOutcome {

    protected RoundTiedOutcome() {
        super(null, true);
    }
}

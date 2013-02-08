package com.jayway.cqrs.sample.handler;

public interface Handler<A> {
    <R> R handle(Object target, A argument) throws Exception;
}
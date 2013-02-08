package com.jayway.cqrs.sample.handler;

import java.lang.reflect.Method;

public class MessageHandler<A> implements Handler<A>  {


    public <R> R handle(Object target, A argument) throws Exception {
        Method foundMethod = findMethod(target, argument);

        return (R) foundMethod.invoke(target, argument);
    }

    private Method findMethod(Object target, A argument) {
        final Method[] methods = target.getClass().getMethods();
        for (Method method : methods) {
            final Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                if(argument.getClass().equals(parameterType))  {
                    return method;
                }
            }
        }
        return null;
    }
}

package me.shaftesbury.reflectionutils;

import io.vavr.control.Try;

import java.lang.reflect.Method;

public class CallMethod {
    public static <CLASS_TYPE, VALUE_TYPE, RETURN_TYPE> On<CLASS_TYPE, WithValue<VALUE_TYPE, RETURN_TYPE>> withName(final String methodName) {
        return obj -> (fieldValue, aClass) -> Try
                .of(() -> {
                    final Method setter = obj.getClass().getMethod(methodName, aClass);
                    return (RETURN_TYPE) setter.invoke(obj, fieldValue);
                }).get();
    }

    public static class OnABuilder {
        public static <CLASS_TYPE, VALUE_TYPE> On<CLASS_TYPE, WithValue<VALUE_TYPE, CLASS_TYPE>> withName(final String methodName) {
            return CallMethod.withName(methodName);
        }
    }
}

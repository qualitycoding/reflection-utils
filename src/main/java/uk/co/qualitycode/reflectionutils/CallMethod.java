package uk.co.qualitycode.reflectionutils;

import io.vavr.control.Try;

public class CallMethod {
    public static <CLASS_TYPE, VALUE_TYPE, RETURN_TYPE> On<CLASS_TYPE, WithValue<VALUE_TYPE, RETURN_TYPE>> withName(final String methodName) {
        return obj -> (fieldValue, aClass) -> Try
                .of(() -> (RETURN_TYPE) obj.getClass().getMethod(methodName, aClass).invoke(obj, fieldValue))
                .get();
    }

    public static class OnABuilder {
        public static <CLASS_TYPE, VALUE_TYPE> On<CLASS_TYPE, WithValue<VALUE_TYPE, CLASS_TYPE>> withName(final String methodName) {
            return CallMethod.withName(methodName);
        }
    }
}

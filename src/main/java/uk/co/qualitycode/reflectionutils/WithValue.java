package uk.co.qualitycode.reflectionutils;

public interface WithValue<A,B> {
    B withValue(A a, final Class<A> aClass);
}

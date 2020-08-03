package uk.co.qualitycode.reflectionutils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CallMethodTest {

    @Test
    void callMethodWithAnInteger() {
        class A {
            public String doIt(final Integer i) {
                return Integer.toString(i);
            }
        }

        final A obj = new A();
        final String actual = CallMethod.<A, Integer, String>withName("doIt").on(obj).withValue(1, Integer.class);
        assertThat(actual).isEqualTo("1");
    }

    @Test
    void callMethodOnBuilder() {
        class A {
            public A doIt(final String i) {
                return this;
            }
        }

        final A obj = new A();
        final A actual = CallMethod.OnABuilder.<A, String>withName("doIt").on(obj).withValue("1", String.class);
        assertThat(actual).isEqualTo(obj);
    }
}

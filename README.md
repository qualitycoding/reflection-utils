# reflection-utils

A very small Java library that wraps method calls made through reflection in a fluent, typed API, so you
don't have to write the usual `getMethod(...).invoke(...)` code and its checked exceptions.
Maven coordinates: `uk.co.qualitycode:reflection-utils` (version `1.0-SNAPSHOT`), Java 8 or later.

> **Status: an early sketch.** It has one entry point and one test class. It only calls public
> methods that take exactly one argument.

## Usage

```java
// Call a one-argument public method by name and get its result back.
String s = CallMethod.<A, Integer, String>withName("doIt").on(obj).withValue(1, Integer.class);

// Same, for a method that returns the receiver's own type (for builders).
A same = CallMethod.OnABuilder.<A, String>withName("doIt").on(obj).withValue("1", String.class);
```

The type parameters are the receiver type, the argument type and the return type. You pass the argument's
`Class` explicitly so the right overload is found.

| Type | Role |
|---|---|
| `CallMethod.withName(name)` | Starts a call: returns an `On` |
| `On<A, B>` | `on(obj)`: picks the object to call the method on |
| `WithValue<A, B>` | `withValue(value, valueClass)`: supplies the argument, runs the call, returns the result |
| `CallMethod.OnABuilder.withName(name)` | The same, typed so the result is the receiver's type |

## Limits

- Only **public** methods are found (`Class.getMethod`), and the lookup uses the object's runtime class.
- Only methods with **exactly one** parameter are supported.
- Failures (no such method, an exception thrown by the method, an access error) are thrown unchecked when
  the result is read: the code uses Vavr's `Try#get()`, which rethrows the original exception.
- The return value is cast without a check, so a wrong return type parameter fails with a
  `ClassCastException` where you use the result.

## Build and test

You need a JDK and Maven. The library depends on [Vavr](https://vavr.io) (`1.0.0-alpha-3`) and
Apache Commons Lang 3; tests use JUnit 5, AssertJ and Mockito.

```bash
mvn test
mvn package
```

## Licence

Apache License 2.0. See [LICENSE](LICENSE).

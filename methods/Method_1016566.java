/** 
 * Wraps the given  {@link ThrowingEx.Function} as a standard {@link java.util.function.Function}, rethrowing any exceptions as runtime exceptions. 
 */
public static <T,R>java.util.function.Function<T,R> wrap(ThrowingEx.Function<T,R> function){
  return input -> {
    try {
      return function.apply(input);
    }
 catch (    Exception t) {
      throw asRuntime(t);
    }
  }
;
}

/** 
 * Wraps checked exceptions in a <code>UncheckedException</code>. Unchecked exceptions are not wrapped.
 */
public static <V>V callAndWrapException(final Callable<V> callable){
  try {
    return callable.call();
  }
 catch (  IOException ioex) {
    throw new UncheckedIOException(ioex);
  }
catch (  RuntimeException rtex) {
    throw rtex;
  }
catch (  Exception t) {
    throw new UncheckedException(t);
  }
}

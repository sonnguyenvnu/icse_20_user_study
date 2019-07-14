/** 
 * Wraps checked exceptions in a <code>UncheckedException</code>. Unchecked exceptions are not wrapped.
 */
public static void runAndWrapException(final CallableVoid callable){
  try {
    callable.call();
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

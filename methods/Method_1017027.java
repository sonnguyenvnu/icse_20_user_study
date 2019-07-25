/** 
 * Guarantee that both clauses are called, in the given order. Combine any thrown exceptions by adding any prior exceptions as suppressed. <p> This is useful if you want to have multiple potentially throwing blocks of code, and guarantee that all have been called at least once.
 * @param a First block to call.
 * @param b Second block to call.
 * @throws Exception If any of the blocks throw an exception.
 */
public static void call(final Runnable a,final Runnable b){
  RuntimeException inner=null;
  try {
    a.run();
  }
 catch (  final RuntimeException e) {
    inner=e;
  }
  try {
    b.run();
  }
 catch (  final RuntimeException e) {
    if (inner != null) {
      e.addSuppressed(inner);
    }
    throw e;
  }
  if (inner != null) {
    throw inner;
  }
}

/** 
 * Re-throws cause if exists.
 */
public void rethrow() throws Throwable {
  if (cause == null) {
    return;
  }
  throw cause;
}

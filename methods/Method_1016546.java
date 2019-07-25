/** 
 * Returns the underlying state, possibly triggering a call to { {@link #calculateState()}. 
 */
protected final T state(){
  if (state == null) {
synchronized (this) {
      if (state == null) {
        try {
          state=calculateState();
        }
 catch (        Exception e) {
          throw ThrowingEx.asRuntime(e);
        }
      }
    }
  }
  return state;
}

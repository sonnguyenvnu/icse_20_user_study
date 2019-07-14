/** 
 * Attempts to close the resource. If an error occurs and an outermost exception is set, then adds the error to the suppression list.
 * @param o the resource to close if Closeable
 * @param outer the outermost error, or null if unset
 * @return the outermost error, or null if unset and successful
 */
private static @Nullable Throwable tryClose(Object o,@Nullable Throwable outer){
  if (o instanceof Closeable) {
    try {
      ((Closeable)o).close();
    }
 catch (    Throwable t) {
      if (outer == null) {
        return t;
      }
      outer.addSuppressed(t);
      return outer;
    }
  }
  return null;
}

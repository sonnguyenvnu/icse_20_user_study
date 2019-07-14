/** 
 * Finds throwing cause in exception stack. Returns throwable object if cause class is matched. Otherwise, returns <code>null</code>.
 */
@SuppressWarnings({"unchecked"}) public static <T extends Throwable>T findCause(Throwable throwable,final Class<T> cause){
  while (throwable != null) {
    if (throwable.getClass().equals(cause)) {
      return (T)throwable;
    }
    throwable=throwable.getCause();
  }
  return null;
}

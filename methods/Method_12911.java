/** 
 * Check whether the given exception is compatible with the specified exception types, as declared in a throws clause.
 * @param ex the exception to check
 * @param declaredExceptions the exception types declared in the throws clause
 * @return whether the given exception is compatible
 */
public static boolean isCompatibleWithThrowsClause(Throwable ex,Class<?>... declaredExceptions){
  if (!isCheckedException(ex)) {
    return true;
  }
  if (declaredExceptions != null) {
    for (    Class<?> declaredException : declaredExceptions) {
      if (declaredException.isInstance(ex)) {
        return true;
      }
    }
  }
  return false;
}

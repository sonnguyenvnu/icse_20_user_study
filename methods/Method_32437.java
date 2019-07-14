/** 
 * Checks if the exception is, or has a cause, of  {@code IllegalInstantException}.
 * @param ex  the exception to check
 * @return true if an {@code IllegalInstantException}
 */
public static boolean isIllegalInstant(Throwable ex){
  if (ex instanceof IllegalInstantException) {
    return true;
  }
  while (ex.getCause() != null && ex.getCause() != ex) {
    return isIllegalInstant(ex.getCause());
  }
  return false;
}

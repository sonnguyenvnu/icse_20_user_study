/** 
 * Check whether triggered exception is ignorable.
 * @param throwable the exception occurred during a command execution
 * @return true if exception is ignorable, otherwise - false
 */
boolean isIgnorable(Throwable throwable){
  if (ignoreExceptions == null || ignoreExceptions.isEmpty()) {
    return false;
  }
  for (  Class<? extends Throwable> ignoreException : ignoreExceptions) {
    if (ignoreException.isAssignableFrom(throwable.getClass())) {
      return true;
    }
  }
  return false;
}

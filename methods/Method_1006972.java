/** 
 * Re-throws the original throwable if it is unchecked, wraps checked exceptions into  {@link RepeatException}.
 */
private static void rethrow(Throwable throwable) throws RuntimeException {
  if (throwable instanceof Error) {
    throw (Error)throwable;
  }
 else   if (throwable instanceof RuntimeException) {
    throw (RuntimeException)throwable;
  }
 else {
    throw new RepeatException("Exception in batch process",throwable);
  }
}

/** 
 * Handy code to re-throw exception caught from GenerationTask
 */
static void rethrow(@NotNull Throwable th) throws GenerationCanceledException, GenerationFailureException {
  if (th instanceof GenerationCanceledException) {
    throw (GenerationCanceledException)th;
  }
 else   if (th instanceof GenerationFailureException) {
    throw (GenerationFailureException)th;
  }
 else   if (th instanceof RuntimeException) {
    throw (RuntimeException)th;
  }
  throw new GenerationFailureException(th);
}

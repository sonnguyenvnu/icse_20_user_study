/** 
 * Returns the cause of the specified  {@code throwable} peeling it recursively, if it is one of the{@link CompletionException},  {@link ExecutionException},  {@link InvocationTargetException}or  {@link ExceptionInInitializerError}. Otherwise returns the  {@code throwable}.
 */
public static Throwable peel(Throwable throwable){
  requireNonNull(throwable,"throwable");
  Throwable cause=throwable.getCause();
  while (cause != null && cause != throwable && (throwable instanceof CompletionException || throwable instanceof ExecutionException || throwable instanceof InvocationTargetException || throwable instanceof ExceptionInInitializerError)) {
    throwable=cause;
    cause=throwable.getCause();
  }
  return throwable;
}

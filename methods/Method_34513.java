/** 
 * Gets actual exception if it's wrapped in  {@link CommandActionExecutionException} or {@link HystrixBadRequestException}.
 * @param e the exception
 * @return unwrapped
 */
public static Throwable unwrapCause(Throwable e){
  if (e instanceof CommandActionExecutionException) {
    return e.getCause();
  }
  if (e instanceof HystrixBadRequestException) {
    return e.getCause();
  }
  return e;
}

private Throwable getCause(HystrixRuntimeException e){
  if (e.getFailureType() != HystrixRuntimeException.FailureType.COMMAND_EXCEPTION) {
    return e;
  }
  Throwable cause=e.getCause();
  if (e.getFallbackException() instanceof FallbackInvocationException) {
    cause=e.getFallbackException().getCause();
    if (cause instanceof HystrixRuntimeException) {
      cause=getCause((HystrixRuntimeException)cause);
    }
  }
 else   if (cause instanceof CommandActionExecutionException) {
    CommandActionExecutionException commandActionExecutionException=(CommandActionExecutionException)cause;
    cause=commandActionExecutionException.getCause();
  }
  return Optional.fromNullable(cause).or(e);
}

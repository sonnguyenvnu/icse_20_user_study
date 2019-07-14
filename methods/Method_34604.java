private Exception wrapWithOnExecutionErrorHook(Throwable t){
  Exception e=getExceptionFromThrowable(t);
  try {
    return executionHook.onExecutionError(this,e);
  }
 catch (  Throwable hookEx) {
    logger.warn("Error calling HystrixCommandExecutionHook.onExecutionError",hookEx);
    return e;
  }
}

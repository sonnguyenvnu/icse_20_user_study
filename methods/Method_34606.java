private Exception wrapWithOnErrorHook(FailureType failureType,Throwable t){
  Exception e=getExceptionFromThrowable(t);
  try {
    return executionHook.onError(this,failureType,e);
  }
 catch (  Throwable hookEx) {
    logger.warn("Error calling HystrixCommandExecutionHook.onError",hookEx);
    return e;
  }
}

private Exception wrapWithOnFallbackErrorHook(Throwable t){
  Exception e=getExceptionFromThrowable(t);
  try {
    if (isFallbackUserDefined()) {
      return executionHook.onFallbackError(this,e);
    }
 else {
      return e;
    }
  }
 catch (  Throwable hookEx) {
    logger.warn("Error calling HystrixCommandExecutionHook.onFallbackError",hookEx);
    return e;
  }
}

private R wrapWithOnExecutionEmitHook(R r){
  try {
    return executionHook.onExecutionEmit(this,r);
  }
 catch (  Throwable hookEx) {
    logger.warn("Error calling HystrixCommandExecutionHook.onExecutionEmit",hookEx);
    return r;
  }
}

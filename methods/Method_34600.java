private Observable<R> handleBadRequestByEmittingError(Exception underlying){
  Exception toEmit=underlying;
  try {
    long executionLatency=System.currentTimeMillis() - executionResult.getStartTimestamp();
    eventNotifier.markEvent(HystrixEventType.BAD_REQUEST,commandKey);
    executionResult=executionResult.addEvent((int)executionLatency,HystrixEventType.BAD_REQUEST);
    Exception decorated=executionHook.onError(this,FailureType.BAD_REQUEST_EXCEPTION,underlying);
    if (decorated instanceof HystrixBadRequestException) {
      toEmit=decorated;
    }
 else {
      logger.warn("ExecutionHook.onError returned an exception that was not an instance of HystrixBadRequestException so will be ignored.",decorated);
    }
  }
 catch (  Exception hookEx) {
    logger.warn("Error calling HystrixCommandExecutionHook.onError",hookEx);
  }
  return Observable.error(toEmit);
}

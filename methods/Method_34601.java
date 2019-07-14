private Observable<R> handleFallbackDisabledByEmittingError(Exception underlying,FailureType failureType,String message){
  logger.debug("Fallback disabled for HystrixCommand so will throw HystrixRuntimeException. ",underlying);
  Exception wrapped=wrapWithOnErrorHook(failureType,underlying);
  return Observable.error(new HystrixRuntimeException(failureType,this.getClass(),getLogMessagePrefix() + " " + message + " and fallback disabled.",wrapped,null));
}

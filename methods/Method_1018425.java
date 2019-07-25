@Around("pointcut()") public Object retry(ProceedingJoinPoint joinPoint) throws Exception {
  Retryable retryable=AnnotationUtils.findAnnotation(getSpecificmethod(joinPoint),Retryable.class);
  Callable<Object> task=() -> getTask(joinPoint);
  Retryer<Object> retryer=null;
  try {
    retryer=RetryerBuilder.newBuilder().retryIfResult(Predicates.isNull()).retryIfExceptionOfType(retryable.exception()).withStopStrategy(StopStrategies.stopAfterAttempt(retryable.attemptNumber())).withWaitStrategy(WaitStrategies.fixedWait(retryable.sleepTime(),retryable.timeUnit())).withRetryListener(retryable.retryListener().newInstance()).build();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    throw e;
  }
  try {
    return retryer.call(task);
  }
 catch (  ExecutionException e) {
    logger.error(e.getMessage(),e);
  }
catch (  RetryException e) {
    logger.error(e.getMessage(),e);
  }
  return null;
}

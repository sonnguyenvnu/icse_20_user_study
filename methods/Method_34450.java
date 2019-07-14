private Throwable hystrixRuntimeExceptionToThrowable(MetaHolder metaHolder,HystrixRuntimeException e){
  if (metaHolder.raiseHystrixExceptionsContains(HystrixException.RUNTIME_EXCEPTION)) {
    return e;
  }
  return getCause(e);
}

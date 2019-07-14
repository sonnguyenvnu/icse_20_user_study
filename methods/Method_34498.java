public boolean raiseHystrixExceptionsContains(HystrixException hystrixException){
  return getRaiseHystrixExceptions().contains(hystrixException);
}

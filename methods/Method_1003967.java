@Override public Object call(final Method method,final Object[] args,@Nullable final AsyncMethodCallback callback,@Nullable final Amount<Long,Time> connectTimeoutOverride) throws Throwable {
  final AtomicLong retryCounter=stats.get(method);
  final AtomicInteger attempts=new AtomicInteger();
  final List<Throwable> exceptions=Lists.newArrayList();
  final ResultCapture capture=new ResultCapture(){
    @Override public void success(){
    }
    @Override public boolean fail(    Throwable t){
      if (!isRetryable(t)) {
        if (debug) {
          LOG.warning(String.format("Call failed with un-retryable exception of [%s]: %s, previous exceptions: %s",t.getClass().getName(),t.getMessage(),combineStackTraces(exceptions)));
        }
        return true;
      }
 else       if (attempts.get() >= retries) {
        exceptions.add(t);
        if (debug) {
          LOG.warning(String.format("Retried %d times, last error: %s, exceptions: %s",attempts.get(),t,combineStackTraces(exceptions)));
        }
        return true;
      }
 else {
        exceptions.add(t);
        if (isAsync() && attempts.incrementAndGet() <= retries) {
          try {
            retryCounter.incrementAndGet();
            invoke(method,args,callback,this,NONBLOCKING_TIMEOUT);
          }
 catch (          Throwable throwable) {
            return fail(throwable);
          }
        }
        return false;
      }
    }
  }
;
  boolean continueLoop;
  do {
    try {
      return invoke(method,args,callback,capture,connectTimeoutOverride);
    }
 catch (    Throwable t) {
      if (!isRetryable(t)) {
        Throwable propagated=t;
        if (!exceptions.isEmpty() && (t instanceof TResourceExhaustedException)) {
          propagated=exceptions.remove(exceptions.size() - 1);
        }
        if (isAsync()) {
          callback.onError(propagated);
        }
 else {
          throw propagated;
        }
      }
    }
    continueLoop=!isAsync() && attempts.incrementAndGet() <= retries;
    if (continueLoop)     retryCounter.incrementAndGet();
  }
 while (continueLoop);
  Throwable lastRetriedException=Iterables.getLast(exceptions);
  if (debug) {
    if (!exceptions.isEmpty()) {
      LOG.warning(String.format("Retried %d times, last error: %s, previous exceptions: %s",attempts.get(),lastRetriedException,combineStackTraces(exceptions)));
    }
 else {
      LOG.warning(String.format("Retried 1 time, last error: %s",lastRetriedException));
    }
  }
  if (!isAsync())   throw lastRetriedException;
  return null;
}

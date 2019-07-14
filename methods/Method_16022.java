@Override public <V>BatchAsyncJobContainer submit(Callable<V> callable,boolean enableTransaction){
  if (shutdown) {
    logger.warn("TransactionBatchAsyncJobContainer is shutdown, fail job number :{}",failCounter.get());
    return this;
  }
  if (!enableTransaction) {
    if (logger.isDebugEnabled()) {
      logger.debug("submit not transaction support job");
    }
    futures.add(executorService.submit(() -> {
      if (shutdown) {
        return null;
      }
      return callable.call();
    }
));
    return this;
  }
  int tmpJobFlag=transactionJobNumber.incrementAndGet();
  if (logger.isDebugEnabled()) {
    logger.debug("submit transaction support job {}",transactionJobNumber);
  }
  TransactionSupportJob<V> translationJob=translationSupportJobWrapper.wrapper(callable);
  Callable<V> proxy=() -> {
    V value=null;
    try {
      if (failCounter.get() > 0 || shutdown) {
        return null;
      }
      value=translationJob.call();
      transactionJobOverCounter.incrementAndGet();
      if (logger.isDebugEnabled()) {
        logger.debug("transaction support job {} success,wait...",tmpJobFlag);
      }
      countDownLatch.await();
      if (failCounter.get() > 0) {
        if (logger.isDebugEnabled()) {
          logger.debug("transaction support job {} success,but other job failed, do rollback only!",tmpJobFlag);
        }
        translationJob.rollBackOnly();
      }
 else {
        if (logger.isDebugEnabled()) {
          logger.debug("transaction support job {} success,commit.",tmpJobFlag);
        }
      }
      translationJob.commit();
    }
 catch (    Exception e) {
      exceptions.add(e);
      failCounter.incrementAndGet();
      logger.warn("transaction support job {} fail.",tmpJobFlag,e);
    }
 finally {
      transactionJobOverCounter.incrementAndGet();
    }
    return value;
  }
;
  futures.add(executorService.submit(proxy));
  return this;
}

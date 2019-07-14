private void waitNewUrl(){
  newUrlLock.lock();
  try {
    if (threadPool.getThreadAlive() == 0 && exitWhenComplete) {
      return;
    }
    newUrlCondition.await(emptySleepTime,TimeUnit.MILLISECONDS);
  }
 catch (  InterruptedException e) {
    logger.warn("waitNewUrl - interrupted, error {}",e);
  }
 finally {
    newUrlLock.unlock();
  }
}

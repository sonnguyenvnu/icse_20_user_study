@SuppressWarnings("InfiniteLoopStatement") @Override public void run(){
  while (true) {
    try {
      for (      TraceAppender traceAppender : watchedAppenders) {
        traceAppender.cleanup();
      }
      TimeUnit.SECONDS.sleep(scanInterval);
    }
 catch (    Throwable e) {
      SelfLog.error("Error occurred while cleaning up logs.",e);
    }
  }
}

private void runWithTimeout(final long timeout){
  runBeforesThenTestThenAfters(new Runnable(){
    public void run(){
      ExecutorService service=Executors.newSingleThreadExecutor();
      Callable<Object> callable=new Callable<Object>(){
        public Object call() throws Exception {
          runTestMethod();
          return null;
        }
      }
;
      Future<Object> result=service.submit(callable);
      service.shutdown();
      try {
        boolean terminated=service.awaitTermination(timeout,TimeUnit.MILLISECONDS);
        if (!terminated) {
          service.shutdownNow();
        }
        result.get(0,TimeUnit.MILLISECONDS);
      }
 catch (      TimeoutException e) {
        addFailure(new TestTimedOutException(timeout,TimeUnit.MILLISECONDS));
      }
catch (      Exception e) {
        addFailure(e);
      }
    }
  }
);
}

public void execute(BulkRequest bulkRequest,long executionId){
  Runnable toRelease=() -> {
  }
;
  boolean bulkRequestSetupSuccessful=false;
  try {
    listener.beforeBulk(executionId,bulkRequest);
    semaphore.acquire();
    toRelease=semaphore::release;
    CountDownLatch latch=new CountDownLatch(1);
    retry.withBackoff(consumer,bulkRequest,new ActionListener<BulkResponse>(){
      @Override public void onResponse(      BulkResponse response){
        try {
          listener.afterBulk(executionId,bulkRequest,response);
        }
  finally {
          semaphore.release();
          latch.countDown();
        }
      }
      @Override public void onFailure(      Exception e){
        try {
          listener.afterBulk(executionId,bulkRequest,e);
        }
  finally {
          semaphore.release();
          latch.countDown();
        }
      }
    }
,Settings.EMPTY);
    bulkRequestSetupSuccessful=true;
    if (concurrentRequests == 0) {
      latch.await();
    }
  }
 catch (  InterruptedException e) {
    Thread.currentThread().interrupt();
    logger.info((Supplier<?>)() -> new ParameterizedMessage("Bulk request {} has been cancelled.",executionId),e);
    listener.afterBulk(executionId,bulkRequest,e);
  }
catch (  Exception e) {
    logger.warn((Supplier<?>)() -> new ParameterizedMessage("Failed to execute bulk request {}.",executionId),e);
    listener.afterBulk(executionId,bulkRequest,e);
  }
 finally {
    if (bulkRequestSetupSuccessful == false) {
      toRelease.run();
    }
  }
}

public void markBatch(int batchSize){
  HystrixThreadEventStream.getInstance().collapserBatchExecuted(collapserKey,batchSize);
}

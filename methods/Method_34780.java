public void collapserBatchExecuted(HystrixCollapserKey collapserKey,int batchSize){
  HystrixCollapserEvent batchExecution=HystrixCollapserEvent.from(collapserKey,HystrixEventType.Collapser.BATCH_EXECUTED,1);
  HystrixCollapserEvent batchAdditions=HystrixCollapserEvent.from(collapserKey,HystrixEventType.Collapser.ADDED_TO_BATCH,batchSize);
  writeOnlyCollapserSubject.onNext(batchExecution);
  writeOnlyCollapserSubject.onNext(batchAdditions);
}

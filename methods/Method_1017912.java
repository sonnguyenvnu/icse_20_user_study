@Override public BatchJobExecution save(BatchJobExecution jobExecution,ProvenanceEventRecordDTO event){
  if (jobExecution == null) {
    return null;
  }
  Stopwatch stopwatch=Stopwatch.createStarted();
  batchStepExecutionProvider.createStepExecution(jobExecution,event);
  log.debug("Time to create step {} ms ",stopwatch.elapsed(TimeUnit.MILLISECONDS));
  return jobExecution;
}

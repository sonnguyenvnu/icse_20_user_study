/** 
 * @param chunkRequest the current request
 * @param stepContribution the step contribution to update
 * @throws Exception if there is a fatal exception
 */
private Throwable process(ChunkRequest<S> chunkRequest,StepContribution stepContribution) throws Exception {
  Chunk<S> chunk=new Chunk<>(chunkRequest.getItems());
  Throwable failure=null;
  try {
    chunkProcessor.process(stepContribution,chunk);
  }
 catch (  SkipLimitExceededException e) {
    failure=e;
  }
catch (  NonSkippableReadException e) {
    failure=e;
  }
catch (  SkipListenerFailedException e) {
    failure=e;
  }
catch (  RetryException e) {
    failure=e;
  }
catch (  JobInterruptedException e) {
    failure=e;
  }
catch (  Exception e) {
    if (chunkProcessor instanceof FaultTolerantChunkProcessor<?,?>) {
      throw e;
    }
 else {
      failure=e;
    }
  }
  return failure;
}

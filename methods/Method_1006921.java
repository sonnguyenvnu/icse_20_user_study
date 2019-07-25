private void scan(final StepContribution contribution,final Chunk<I> inputs,final Chunk<O> outputs,ChunkMonitor chunkMonitor,boolean recovery) throws Exception {
  @SuppressWarnings("unchecked") final UserData<O> data=(UserData<O>)inputs.getUserData();
  if (logger.isDebugEnabled()) {
    if (recovery) {
      logger.debug("Scanning for failed item on recovery from write: " + inputs);
    }
 else {
      logger.debug("Scanning for failed item on write: " + inputs);
    }
  }
  if (outputs.isEmpty() || inputs.isEmpty()) {
    data.scanning(false);
    inputs.setBusy(false);
    chunkMonitor.resetOffset();
    return;
  }
  Chunk<I>.ChunkIterator inputIterator=inputs.iterator();
  Chunk<O>.ChunkIterator outputIterator=outputs.iterator();
  if (!inputs.getSkips().isEmpty()) {
    if (outputIterator.hasNext()) {
      outputIterator.remove();
      return;
    }
  }
  List<O> items=Collections.singletonList(outputIterator.next());
  inputIterator.next();
  try {
    writeItems(items);
    doAfterWrite(items);
    contribution.incrementWriteCount(1);
    inputIterator.remove();
    outputIterator.remove();
  }
 catch (  Exception e) {
    try {
      doOnWriteError(e,items);
    }
  finally {
      Throwable cause=e;
      if (e instanceof StepListenerFailedException) {
        cause=e.getCause();
      }
      if (!shouldSkip(itemWriteSkipPolicy,cause,-1) && !rollbackClassifier.classify(cause)) {
        inputIterator.remove();
        outputIterator.remove();
      }
 else {
        checkSkipPolicy(inputIterator,outputIterator,cause,contribution,recovery);
      }
      if (rollbackClassifier.classify(cause)) {
        throw (Exception)cause;
      }
    }
  }
  chunkMonitor.incrementOffset();
  if (outputs.isEmpty()) {
    data.scanning(false);
    inputs.setBusy(false);
    chunkMonitor.resetOffset();
  }
}

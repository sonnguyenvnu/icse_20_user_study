/** 
 * Adds retry and skip logic to the process phase of the chunk loop.
 * @param contribution a {@link StepContribution}
 * @param item an item to be processed
 * @return O an item that has been processed if a processor is available
 * @throws Exception thrown if error occurs.
 */
@Override @SuppressWarnings("unchecked") protected O transform(final StepContribution contribution,final I item) throws Exception {
  if (!hasProcessor) {
    return (O)item;
  }
  RetryCallback<O,Exception> retryCallback=new RetryCallback<O,Exception>(){
    @Override public O doWithRetry(    RetryContext context) throws Exception {
      try {
        return doTransform(item);
      }
 catch (      Exception e) {
        if (shouldSkip(skipPolicy,e,contribution.getStepSkipCount())) {
          contribution.incrementProcessSkipCount();
          logger.debug("Skipping after failed process with no rollback",e);
          getListener().onSkipInProcess(item,e);
        }
 else {
          getListener().onRetryProcessException(item,e);
          if (rollbackClassifier.classify(e)) {
            throw e;
          }
 else {
            throw e;
          }
        }
      }
      return null;
    }
  }
;
  RecoveryCallback<O> recoveryCallback=new RecoveryCallback<O>(){
    @Override public O recover(    RetryContext context) throws Exception {
      Throwable e=context.getLastThrowable();
      if (shouldSkip(skipPolicy,e,contribution.getStepSkipCount())) {
        contribution.incrementProcessSkipCount();
        logger.debug("Skipping after failed process",e);
        return null;
      }
 else {
        if (rollbackClassifier.classify(e)) {
          throw new RetryException("Non-skippable exception in recoverer while processing",e);
        }
        throw new BatchRuntimeException(e);
      }
    }
  }
;
  return batchRetryTemplate.execute(retryCallback,recoveryCallback);
}

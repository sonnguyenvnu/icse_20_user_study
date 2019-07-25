/** 
 * Adds retry and skip logic to the reading phase of the chunk loop.
 * @param contribution a {@link StepContribution}
 * @param chunk a {@link Chunk}
 * @return I an item
 * @throws Exception thrown if error occurs.
 */
@Override protected I provide(final StepContribution contribution,final Chunk<I> chunk) throws Exception {
  RetryCallback<I,Exception> retryCallback=new RetryCallback<I,Exception>(){
    @Override public I doWithRetry(    RetryContext arg0) throws Exception {
      while (true) {
        try {
          return doProvide(contribution,chunk);
        }
 catch (        Exception e) {
          if (shouldSkip(skipPolicy,e,contribution.getStepSkipCount())) {
            contribution.incrementReadSkipCount();
            chunk.skip(e);
            getListener().onSkipInRead(e);
            logger.debug("Skipping failed input",e);
          }
 else {
            getListener().onRetryReadException(e);
            if (rollbackClassifier.classify(e)) {
              throw e;
            }
 else {
              throw e;
            }
          }
        }
      }
    }
  }
;
  RecoveryCallback<I> recoveryCallback=new RecoveryCallback<I>(){
    @Override public I recover(    RetryContext context) throws Exception {
      Throwable e=context.getLastThrowable();
      if (shouldSkip(skipPolicy,e,contribution.getStepSkipCount())) {
        contribution.incrementReadSkipCount();
        logger.debug("Skipping after failed process",e);
        return null;
      }
 else {
        if (rollbackClassifier.classify(e)) {
          throw new RetryException("Non-skippable exception in recoverer while reading",e);
        }
        throw new BatchRuntimeException(e);
      }
    }
  }
;
  return batchRetryTemplate.execute(retryCallback,recoveryCallback);
}

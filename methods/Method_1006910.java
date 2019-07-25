/** 
 * Explicitly ask for an exception (and subclasses) to be retried.
 * @param type the exception to retry
 * @return this for fluent chaining
 */
public FaultTolerantStepBuilder<I,O> retry(Class<? extends Throwable> type){
  retryableExceptionClasses.put(type,true);
  return this;
}

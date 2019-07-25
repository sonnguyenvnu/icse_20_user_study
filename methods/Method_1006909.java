/** 
 * Explicitly request certain exceptions (and subclasses) to be skipped.
 * @param type the exception type.
 * @return this for fluent chaining
 */
public FaultTolerantStepBuilder<I,O> skip(Class<? extends Throwable> type){
  skippableExceptionClasses.put(type,true);
  return this;
}

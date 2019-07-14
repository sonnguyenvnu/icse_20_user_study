/** 
 * Specifies that retries should be aborted if the execution result matches the  {@code result}.
 */
public RetryPolicy<R> abortWhen(R result){
  abortConditions.add(resultPredicateFor(result));
  return this;
}

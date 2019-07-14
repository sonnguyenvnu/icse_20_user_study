/** 
 * Returns whether an execution result can be retried given the configured failure conditions.
 * @see #handle(Class)
 * @see #handle(List)
 * @see #handleIf(BiPredicate)
 * @see #handleIf(Predicate)
 * @see #handleResult(R)
 * @see #handleResultIf(Predicate)
 */
public boolean isFailure(R result,Throwable failure){
  for (  BiPredicate<R,Throwable> predicate : failureConditions) {
    try {
      if (predicate.test(result,failure))       return true;
    }
 catch (    Exception ignored) {
    }
  }
  return failure != null && !failuresChecked;
}

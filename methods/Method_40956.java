/** 
 * Specifies that retries should be aborted if the  {@code failurePredicate} matches the failure.
 * @throws NullPointerException if {@code failurePredicate} is null
 */
public RetryPolicy<R> abortOn(Predicate<? extends Throwable> failurePredicate){
  Assert.notNull(failurePredicate,"failurePredicate");
  abortConditions.add(failurePredicateFor(failurePredicate));
  return this;
}

/** 
 * Specifies that retries should be aborted if the  {@code resultPredicate} matches the result. Predicate is notinvoked when the operation fails.
 * @throws NullPointerException if {@code resultPredicate} is null
 */
public RetryPolicy<R> abortIf(Predicate<R> resultPredicate){
  Assert.notNull(resultPredicate,"resultPredicate");
  abortConditions.add(resultPredicateFor(resultPredicate));
  return this;
}

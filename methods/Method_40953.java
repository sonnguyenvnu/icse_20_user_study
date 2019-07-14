/** 
 * Specifies that retries should be aborted if the  {@code completionPredicate} matches the completion result.
 * @throws NullPointerException if {@code completionPredicate} is null
 */
@SuppressWarnings("unchecked") public RetryPolicy<R> abortIf(BiPredicate<R,? extends Throwable> completionPredicate){
  Assert.notNull(completionPredicate,"completionPredicate");
  abortConditions.add((BiPredicate<R,Throwable>)completionPredicate);
  return this;
}

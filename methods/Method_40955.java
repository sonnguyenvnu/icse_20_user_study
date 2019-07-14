/** 
 * Specifies when retries should be aborted. Any failure that is assignable from the  {@code failures} will be resultin retries being aborted.
 * @throws NullPointerException if {@code failures} is null
 * @throws IllegalArgumentException if failures is null or empty
 */
public RetryPolicy<R> abortOn(List<Class<? extends Throwable>> failures){
  Assert.notNull(failures,"failures");
  Assert.isTrue(!failures.isEmpty(),"failures cannot be empty");
  abortConditions.add(failurePredicateFor(failures));
  return this;
}

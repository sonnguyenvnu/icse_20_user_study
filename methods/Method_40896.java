/** 
 * Specifies that a failure has occurred if the  {@code resultPredicate} matches the execution result.
 * @throws NullPointerException if {@code resultPredicate} is null
 */
@SuppressWarnings("unchecked") public S handleIf(BiPredicate<R,? extends Throwable> resultPredicate){
  Assert.notNull(resultPredicate,"resultPredicate");
  failuresChecked=true;
  failureConditions.add((BiPredicate<R,Throwable>)resultPredicate);
  return (S)this;
}

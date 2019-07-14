/** 
 * Specifies that a failure has occurred if the  {@code failurePredicate} matches the failure.
 * @throws NullPointerException if {@code failurePredicate} is null
 */
public S handleIf(Predicate<? extends Throwable> failurePredicate){
  Assert.notNull(failurePredicate,"failurePredicate");
  failuresChecked=true;
  failureConditions.add(failurePredicateFor(failurePredicate));
  return (S)this;
}

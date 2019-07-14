/** 
 * Specifies that a failure has occurred if the  {@code result} matches the execution result.
 */
public S handleResult(R result){
  failureConditions.add(resultPredicateFor(result));
  return (S)this;
}

/** 
 * Specifies the failures to handle. Any failures that are assignable from the  {@code failures} will be handled.
 * @throws NullPointerException if {@code failures} is null
 * @throws IllegalArgumentException if failures is null or empty
 */
public S handle(List<Class<? extends Throwable>> failures){
  Assert.notNull(failures,"failures");
  Assert.isTrue(!failures.isEmpty(),"failures cannot be empty");
  failuresChecked=true;
  failureConditions.add(failurePredicateFor(failures));
  return (S)this;
}

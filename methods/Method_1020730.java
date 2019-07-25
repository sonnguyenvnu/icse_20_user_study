/** 
 * Matches when all observable sequences have an available element and projects the elements by invoking the selector function.
 * @param < R > the result type
 * @param selector the function that will be invoked for elements in the source sequences.
 * @return the plan for the matching
 * @throws NullPointerException if selector is null
 */
public <R>Plan<R> then(Function7<T1,T2,T3,T4,T5,T6,T7,R> selector){
  if (selector == null) {
    throw new NullPointerException();
  }
  return new Plan7<T1,T2,T3,T4,T5,T6,T7,R>(this,selector);
}

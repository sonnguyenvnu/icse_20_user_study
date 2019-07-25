/** 
 * Returns <code>true</code> if this optional value has no value, or the predicate holds for the given predicate function, <code>false</code> otherwise.
 * @param f the predicate function to test on the value of this optional value.
 * @return <code>true</code> if this optional value has no value, or the predicate holds for thegiven predicate function, <code>false</code> otherwise.
 */
public final boolean forall(final F<A,Boolean> f){
  return isNone() || f.f(some());
}

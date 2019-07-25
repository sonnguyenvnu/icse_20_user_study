/** 
 * Returns <code>true</code> is this optional value has a value and the given predicate function holds on that value, <code>false</code> otherwise.
 * @param f the predicate function to test on the value of this optional value.
 * @return <code>true</code> is this optional value has a value and the given predicate functionholds on that value, <code>false</code> otherwise.
 */
public final boolean exists(final F<A,Boolean> f){
  return isSome() && f.f(some());
}

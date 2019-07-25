/** 
 * Returns <code>true</code> if the predicate holds for all of the elements of this list, <code>false</code> otherwise (<code>true</code> for the empty list).
 * @param f The predicate function to test on each element of this list.
 * @return <code>true</code> if the predicate holds for all of the elements of this list,<code>false</code> otherwise.
 */
public final boolean forall(final F<A,Boolean> f){
  return isEmpty() || f.f(head()) && tail().forall(f);
}

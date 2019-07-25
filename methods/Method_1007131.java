/** 
 * Filters elements from this set by returning only elements which produce <code>true</code> when the given function is applied to them.
 * @param f The predicate function to filter on.
 * @return A new set whose elements all match the given predicate.
 */
public final Set<A> filter(final F<A,Boolean> f){
  return iterableSet(ord,toStream().filter(f));
}

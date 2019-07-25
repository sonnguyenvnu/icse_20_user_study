/** 
 * Remove all elements from this set that occur in the given set.
 * @param s A set of elements to delete.
 * @return A new set which contains only the elements of this set that do not occur in the given set.
 */
public final Set<A> minus(final Set<A> s){
  return filter(compose(not,Set.<A>member().f(s)));
}

/** 
 * Sorts this non empty list using the given order over elements using a <em>merge sort</em> algorithm.
 * @param o The order over the elements of this non empty list.
 * @return A sorted non empty list according to the given order.
 */
public NonEmptyList<A> sort(final Ord<A> o){
  final List<A> list=toList().sort(o);
  return nel(list.head(),list.tail());
}

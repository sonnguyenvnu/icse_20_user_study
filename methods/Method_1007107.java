/** 
 * Reverse this non empty list in constant stack space.
 * @return A new non empty list with the elements in reverse order.
 */
public NonEmptyList<A> reverse(){
  final List<A> list=toList().reverse();
  return nel(list.head(),list.tail());
}

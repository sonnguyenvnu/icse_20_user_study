/** 
 * Intersperses the given argument between each element of this non empty list.
 * @param a The separator to intersperse in this non empty list.
 * @return A non empty list with the given separator interspersed.
 */
public NonEmptyList<A> intersperse(final A a){
  final List<A> list=toList().intersperse(a);
  return nel(list.head(),list.tail());
}

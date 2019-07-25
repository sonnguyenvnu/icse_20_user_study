/** 
 * Returns the size of this set.
 * @return The number of elements in this set.
 */
public final int size(){
  final F<A,Integer> one=constant(1);
  return foldMap(one,Monoid.intAdditionMonoid);
}

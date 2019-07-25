/** 
 * Joins the given array of arrays using a bind operation.
 * @param o The array of arrays to join.
 * @return A new array that is the join of the given arrays.
 */
public static <A>Array<A> join(final Array<Array<A>> o){
  final F<Array<A>,Array<A>> id=identity();
  return o.bind(id);
}

/** 
 * Joins an Iterable of Iterables into a single Iterable.
 * @param as An Iterable of Iterables to join.
 * @return the joined Iterable.
 */
public static <A,T extends Iterable<A>>IterableW<A> join(final Iterable<T> as){
  final F<T,T> id=identity();
  return wrap(as).bind(id);
}

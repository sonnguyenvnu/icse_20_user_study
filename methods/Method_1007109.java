/** 
 * Return a non-empty list with the given head and tail.
 * @param head The first element of the new list.
 * @param tail The remaining elements of the new list.
 * @return A non-empty list with the given head and tail.
 */
public static <A>NonEmptyList<A> nel(final A head,final List<A> tail){
  return new NonEmptyList<>(head,tail);
}

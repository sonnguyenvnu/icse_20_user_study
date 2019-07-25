/** 
 * Concatenate (join) a non empty list of non empty lists.
 * @param o The non empty list of non empty lists to join.
 * @return A new non empty list that is the concatenation of the given lists.
 */
public static <A>NonEmptyList<A> join(final NonEmptyList<NonEmptyList<A>> o){
  return o.bind(identity());
}

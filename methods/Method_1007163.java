/** 
 * Creates a vector-8 from a head and a tail.
 * @param head The value to put as the first element of the vector.
 * @param tail The vector representing all but the first element of the new vector.
 * @return The new vector.
 */
public static <A>V8<A> cons(final P1<A> head,final V7<A> tail){
  return new V8<>(head,tail);
}

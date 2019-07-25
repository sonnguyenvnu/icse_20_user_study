/** 
 * Returns a function that puts an element into a non-empty list.
 * @return A function that puts an element into a non-empty list.
 */
public static <A>F<A,NonEmptyList<A>> nel(){
  return a -> nel(a);
}

/** 
 * Returns a function that joins an Iterable of Iterables into a single Iterable.
 * @return a function that joins an Iterable of Iterables into a single Iterable.
 */
public static <A,T extends Iterable<A>>F<Iterable<T>,IterableW<A>> join(){
  return IterableW::join;
}

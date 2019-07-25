/** 
 * Uncurries this function to a function on tuples.
 * @return A new function that calls this function with the elements of a given tuple.
 */
public static <A,B,C>F<P2<A,B>,C> tuple(final F2<A,B,C> f){
  return p -> f.f(p._1(),p._2());
}

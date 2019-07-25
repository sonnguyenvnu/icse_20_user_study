/** 
 * Uncurries this function to a function on tuples.
 * @return A new function that calls this function with the elements of a given tuple.
 */
public final F1W<P2<A,B>,C> tuple(){
  return F1W.lift(F2Functions.tuple(this));
}

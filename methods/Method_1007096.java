/** 
 * Returns an associated value with the given key in the list of pairs.
 * @param e The test for equality on keys.
 * @param x The list of pairs to search.
 * @param a The key value to find the associated value of.
 * @return An associated value with the given key in the list of pairs.
 */
public static <A,B>Option<B> lookup(final Equal<A> e,final List<P2<A,B>> x,final A a){
  return x.find(p -> e.eq(p._1(),a)).map(P2.__2());
}

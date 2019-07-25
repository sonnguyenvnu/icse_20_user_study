/** 
 * Returns a partially applied version of  {@link #lookup(Equal,List,Object)}.
 * @param e The test for equality on keys.
 * @return A partially applied version of {@link #lookup(Equal,List,Object)}.
 */
public static <A,B>F2<List<P2<A,B>>,A,Option<B>> lookup(final Equal<A> e){
  return (x,a) -> lookup(e,x,a);
}

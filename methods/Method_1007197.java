/** 
 * Creates a  {@link P4} by adding the given element to the current {@link P2}
 * @param el the element to append
 * @return A {@link P4} containing the original {@link P2} with the extra element added at the end
 */
public final <C,D>P4<A,B,C,D> append(P2<C,D> el){
  return P.p(_1(),_2(),el._1(),el._2());
}

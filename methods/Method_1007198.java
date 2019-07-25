/** 
 * Creates a  {@link P5} by adding the given element to the current {@link P2}
 * @param el the element to append
 * @return A {@link P5} containing the original {@link P2} with the extra element added at the end
 */
public final <C,D,E>P5<A,B,C,D,E> append(P3<C,D,E> el){
  return P.p(_1(),_2(),el._1(),el._2(),el._3());
}

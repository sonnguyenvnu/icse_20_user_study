/** 
 * Creates a  {@link P7} by adding the given element to the current {@link P2}
 * @param el the element to append
 * @return A {@link P7} containing the original {@link P2} with the extra element added at the end
 */
public final <C,D,E,F,G>P7<A,B,C,D,E,F,G> append(P5<C,D,E,F,G> el){
  return P.p(_1(),_2(),el._1(),el._2(),el._3(),el._4(),el._5());
}

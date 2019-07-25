/** 
 * Creates a  {@link P8} by adding the given element to the current {@link P6}
 * @param el the element to append
 * @return A {@link P8} containing the original {@link P6} with the extra element added at the end
 */
public final <G,H>P8<A,B,C,D,E,F,G,H> append(P2<G,H> el){
  return P.p(_1(),_2(),_3(),_4(),_5(),_6(),el._1(),el._2());
}

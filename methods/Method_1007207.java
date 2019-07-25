/** 
 * Creates a  {@link P7} by adding the given element to the current {@link P6}
 * @param el the element to append
 * @return A {@link P7} containing the original {@link P6} with the extra element added at the end
 */
public final <G>P7<A,B,C,D,E,F,G> append(G el){
  return P.p(_1(),_2(),_3(),_4(),_5(),_6(),el);
}

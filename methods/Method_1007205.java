/** 
 * Creates a  {@link P6} by adding the given element to the current {@link P5}
 * @param el the element to append
 * @return A {@link P6} containing the original {@link P5} with the extra element added at the end
 */
public final <F>P6<A,B,C,D,E,F> append(F el){
  return P.p(_1(),_2(),_3(),_4(),_5(),el);
}

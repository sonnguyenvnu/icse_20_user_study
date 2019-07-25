/** 
 * Creates a function that can extract the first item of pairs of the given type parametrization.
 * @param < S > The type of the 1st item in the pair.
 * @param < T > The type of the 2nd item in the pair.
 * @return A function that will extract the 1st item in a pair.
 */
public static <S,T>Function<Pair<S,T>,S> first(){
  return new Function<Pair<S,T>,S>(){
    @Override public S apply(    Pair<S,T> pair){
      return pair.first;
    }
  }
;
}

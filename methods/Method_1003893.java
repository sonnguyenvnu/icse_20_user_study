/** 
 * Creates a function that can extract the second item of pairs of the given type parametrization.
 * @param < S > The type of the 1st item in the pair.
 * @param < T > The type of the 2nd item in the pair.
 * @return A function that will extract the 2nd item in a pair.
 */
public static <S,T>Function<Pair<S,T>,T> second(){
  return new Function<Pair<S,T>,T>(){
    @Override public T apply(    Pair<S,T> pair){
      return pair.second;
    }
  }
;
}

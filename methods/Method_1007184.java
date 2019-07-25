/** 
 * Curry a function of arity-4.
 * @param f The function to curry.
 * @return A curried form of the given function.
 */
public static <A,B,C,D,E>F<A,F<B,F<C,F<D,E>>>> curry(final F4<A,B,C,D,E> f){
  return a -> b -> c -> d -> f.f(a,b,c,d);
}

/** 
 * Promotes the given function so that it returns its value in a P1.
 * @param f A function to have its result wrapped in a P1.
 * @return A function whose result is wrapped in a P1.
 */
public static <A,B>F<A,P1<B>> curry(final F<A,B> f){
  return a -> P.lazy(() -> f.f(a));
}

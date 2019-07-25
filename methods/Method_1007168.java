/** 
 * Promotes this function so that it returns its result in a product-1. Kleisli arrow for P1.
 * @return This function promoted to return its result in a product-1.
 */
public static <A,B>F<A,P1<B>> lazy(final F<A,B> f){
  return a -> P.lazy(() -> f.f(a));
}

/** 
 * Duplicates this product on the first element, and maps the given function across the duplicate (Comonad pattern).
 * @param k A function to map over the duplicated product.
 * @return A new product with the result of the given function applied to this product as the first element,and with the second element intact.
 */
public final <C>P2<C,B> cobind(final F<P2<A,B>,C> k){
  P2<A,B> self=this;
  return P.lazy(() -> k.f(self),self::_2);
}

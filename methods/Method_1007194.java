/** 
 * Swaps the elements around in this product.
 * @return A new product-2 with the elements swapped.
 */
public final P2<B,A> swap(){
  return P.lazy(P2.this::_2,P2.this::_1);
}

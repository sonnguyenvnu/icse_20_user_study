/** 
 * Return the first element of this vector as a product-1.
 * @return the first element of this vector as a product-1.
 */
public P1<A> head(){
  return P.lazy(V2.this::_1);
}

/** 
 * Split this product between two argument functions and combine their output.
 * @param f A function that will map the first element of this product.
 * @param g A function that will map the second element of this product.
 * @return A new product with the first function applied to the second elementand the second function applied to the second element.
 */
public final <C,D>P2<C,D> split(final F<A,C> f,final F<B,D> g){
  final F<P2<A,D>,P2<C,D>> ff=map1_(f);
  final F<P2<A,B>,P2<A,D>> gg=map2_(g);
  return compose(ff,gg).f(this);
}

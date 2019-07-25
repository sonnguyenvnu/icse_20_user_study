/** 
 * A first-class version of the split function.
 * @param f A function that will map the first element of the given product.
 * @param g A function that will map the second element of the given product.
 * @return A function that splits a given product between the two given functions and combines their output.
 */
public static <A,B,C,D>F<P2<A,B>,P2<C,D>> split_(final F<A,C> f,final F<B,D> g){
  return p -> p.split(f,g);
}

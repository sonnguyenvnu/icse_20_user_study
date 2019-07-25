/** 
 * Creates an empty finger tree with elements of type A and node annotations of type V.
 * @param m A monoid to combine node annotations
 * @param f Function to convert node element to annotation.
 * @return An empty finger tree.
 */
public static <V,A>FingerTree<V,A> empty(Monoid<V> m,F<A,V> f){
  return FingerTree.mkTree(measured(m,f)).empty();
}

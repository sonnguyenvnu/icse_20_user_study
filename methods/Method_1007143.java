/** 
 * Creates a nullary tree.
 * @param root The root element of the tree.
 * @return A nullary tree with the root element in it.
 */
public static <A>Tree<A> leaf(final A root){
  return node(root,Stream.nil());
}

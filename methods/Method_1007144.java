/** 
 * Provides a transformation from a tree to its root.
 * @return A transformation from a tree to its root.
 */
public static <A>F<Tree<A>,A> root_(){
  return Tree::root;
}

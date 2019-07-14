/** 
 * Matches an AST node which is the same object reference as the given node. 
 */
public static <T extends Tree>Matcher<T> isSame(Tree t){
  return (tree,state) -> tree == t;
}

/** 
 * Given a TreePath, walks up the tree until it finds a node of the given type. Returns null if no such node is found.
 */
@Nullable public static <T>T findEnclosingNode(TreePath path,Class<T> klass){
  path=findPathFromEnclosingNodeToTopLevel(path,klass);
  return (path == null) ? null : klass.cast(path.getLeaf());
}

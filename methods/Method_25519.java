/** 
 * Given a TreePath, finds the first enclosing node of the given type and returns the path from the enclosing node to the top-level  {@code CompilationUnitTree}.
 */
public static <T>TreePath findPathFromEnclosingNodeToTopLevel(TreePath path,Class<T> klass){
  if (path != null) {
    do {
      path=path.getParentPath();
    }
 while (path != null && !(klass.isInstance(path.getLeaf())));
  }
  return path;
}

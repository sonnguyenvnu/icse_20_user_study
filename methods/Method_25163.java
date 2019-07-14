@Nullable private static <T>TreePath findEnclosingMethodOrLambdaOrInitializer(TreePath path){
  while (path != null) {
    if (path.getLeaf() instanceof MethodTree) {
      return path;
    }
    TreePath parent=path.getParentPath();
    if (parent != null) {
      if (parent.getLeaf() instanceof ClassTree) {
        if (path.getLeaf() instanceof BlockTree) {
          return path;
        }
        if (path.getLeaf() instanceof VariableTree && ((VariableTree)path.getLeaf()).getInitializer() != null) {
          return path;
        }
      }
      if (parent.getLeaf() instanceof LambdaExpressionTree) {
        return parent;
      }
    }
    path=parent;
  }
  return null;
}

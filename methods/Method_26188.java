@Nullable private static JCMethodDecl findSurroundingMethod(TreePath path){
  while (path.getLeaf().getKind() != Kind.METHOD) {
    if (path.getLeaf().getKind() == Kind.LAMBDA_EXPRESSION) {
      return null;
    }
    path=path.getParentPath();
  }
  return (JCMethodDecl)path.getLeaf();
}

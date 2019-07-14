private static Optional<TreePath> getEnclosingMethod(TreePath path){
  while (path != null && path.getLeaf().getKind() != Kind.CLASS && path.getLeaf().getKind() != Kind.LAMBDA_EXPRESSION) {
    if (path.getLeaf().getKind() == Kind.METHOD) {
      return Optional.of(path);
    }
    path=path.getParentPath();
  }
  return Optional.empty();
}

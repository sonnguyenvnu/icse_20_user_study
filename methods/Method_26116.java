private static boolean fluentBuilderUsed(VisitorState state){
  for (TreePath path=state.getPath(); path != null; path=path.getParentPath().getParentPath()) {
    if (path.getParentPath().getLeaf() instanceof ExpressionStatementTree) {
      return false;
    }
    if (!(path.getParentPath().getLeaf() instanceof MemberSelectTree && path.getParentPath().getParentPath().getLeaf() instanceof MethodInvocationTree && FLUENT_CHAIN.matches((MethodInvocationTree)path.getParentPath().getParentPath().getLeaf(),state))) {
      return true;
    }
  }
  return true;
}

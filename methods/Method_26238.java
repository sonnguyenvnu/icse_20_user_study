@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return NO_MATCH;
  }
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (!(parent instanceof TypeCastTree)) {
    return NO_MATCH;
  }
  if (!((TypeCastTree)parent).getExpression().equals(tree)) {
    return NO_MATCH;
  }
  Type type=ASTHelpers.getType(parent);
  if (type == null || !INTEGRAL.contains(type.getKind())) {
    return NO_MATCH;
  }
  return describeMatch(tree);
}

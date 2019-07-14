@Override public final boolean matches(ExpressionTree tree,VisitorState state){
  return matchResult(tree,state).isPresent();
}

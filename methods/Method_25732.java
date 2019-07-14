@Override protected Optional<Fix> implicitToStringFix(ExpressionTree tree,VisitorState state){
  return toStringFix(tree,tree,state);
}

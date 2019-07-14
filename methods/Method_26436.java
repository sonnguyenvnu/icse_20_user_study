@Override protected Optional<Fix> implicitToStringFix(ExpressionTree tree,VisitorState state){
  return fix(tree,tree,state);
}

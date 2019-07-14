@Override public boolean matches(ExpressionTree expr,VisitorState state){
  TreePath exprPath=new TreePath(state.getPath(),expr);
  return state.getNullnessAnalysis().getNullness(exprPath,state.context) == expectedNullnessValue;
}

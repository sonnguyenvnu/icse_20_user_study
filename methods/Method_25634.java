private Nullness getNullness(ExpressionTree expr,VisitorState state){
  TreePath pathToExpr=new TreePath(state.getPath(),expr);
  return state.getNullnessAnalysis().getNullness(pathToExpr,state.context);
}

/** 
 * Returns the  {@link Nullness} for an expression as determined by the nullness dataflow analysis.
 */
public static Nullness getNullnessValue(ExpressionTree expr,VisitorState state,NullnessAnalysis nullnessAnalysis){
  TreePath pathToExpr=new TreePath(state.getPath(),expr);
  return nullnessAnalysis.getNullness(pathToExpr,state.context);
}

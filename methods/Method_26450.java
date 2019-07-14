private static String getMessageSnippet(StatementTree failStatement,VisitorState state,HasOtherParameters hasOtherParameters){
  ExpressionTree expression=((ExpressionStatementTree)failStatement).getExpression();
  MethodSymbol sym=(MethodSymbol)getSymbol(expression);
  String tail=hasOtherParameters == HasOtherParameters.TRUE ? ", " : "";
  return hasInitialStringParameter(sym,state) ? state.getSourceForNode(((MethodInvocationTree)expression).getArguments().get(0)) + tail : "";
}

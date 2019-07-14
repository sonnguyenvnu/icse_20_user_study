private static boolean buildChain(ExpressionTree expr,VisitorState state,List<Symbol> chain){
  while (expr instanceof JCMethodInvocation) {
    expr=((JCMethodInvocation)expr).getMethodSelect();
    if (!IS_IMMUTABLE_PROTO_GETTER.matches(expr,state)) {
      return false;
    }
    if (expr instanceof JCFieldAccess) {
      expr=((JCFieldAccess)expr).getExpression();
    }
    chain.add(ASTHelpers.getSymbol(expr));
  }
  return true;
}

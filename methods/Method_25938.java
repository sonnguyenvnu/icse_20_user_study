@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (!sym.isVarArgs()) {
    return NO_MATCH;
  }
  if (tree.getArguments().size() != sym.getParameters().size()) {
    return NO_MATCH;
  }
  Tree arg=getLast(tree.getArguments());
  if (!(arg instanceof ConditionalExpressionTree)) {
    return NO_MATCH;
  }
  Types types=state.getTypes();
  if (types.isArray(getType(arg))) {
    return NO_MATCH;
  }
  ConditionalExpressionTree cond=(ConditionalExpressionTree)arg;
  boolean trueIsArray=types.isArray(getType(cond.getTrueExpression()));
  if (!(trueIsArray ^ types.isArray(getType(cond.getFalseExpression())))) {
    return NO_MATCH;
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String qualified=SuggestedFixes.qualifyType(state,fix,types.elemtype(getLast(sym.getParameters()).asType()));
  Tree toFix=!trueIsArray ? cond.getTrueExpression() : cond.getFalseExpression();
  fix.prefixWith(toFix,String.format("new %s[] {",qualified)).postfixWith(toFix,"}");
  return describeMatch(tree,fix.build());
}

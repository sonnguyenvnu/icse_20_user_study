@Override public Description matchMethodInvocation(MethodInvocationTree invocationTree,final VisitorState state){
  if (!ARRAY_FILL_MATCHER.matches(invocationTree,state)) {
    return Description.NO_MATCH;
  }
  Type arrayComponentType=state.getTypes().elemtype(ASTHelpers.getType(invocationTree.getArguments().get(0)));
  Tree fillingArgument=Iterables.getLast(invocationTree.getArguments());
  Type fillingObjectType=ASTHelpers.getType(fillingArgument);
  if (isValidArrayFill(state,arrayComponentType,fillingObjectType)) {
    return Description.NO_MATCH;
  }
  if (fillingArgument.getKind() == Kind.CONDITIONAL_EXPRESSION) {
    ConditionalExpressionTree cet=(ConditionalExpressionTree)fillingArgument;
    Type trueExpressionType=ASTHelpers.getType(cet.getTrueExpression());
    if (!isValidArrayFill(state,arrayComponentType,trueExpressionType)) {
      return reportMismatch(invocationTree,arrayComponentType,trueExpressionType);
    }
    Type falseExpressionType=ASTHelpers.getType(cet.getFalseExpression());
    if (!isValidArrayFill(state,arrayComponentType,falseExpressionType)) {
      return reportMismatch(invocationTree,arrayComponentType,falseExpressionType);
    }
    return Description.NO_MATCH;
  }
  return reportMismatch(invocationTree,arrayComponentType,fillingObjectType);
}

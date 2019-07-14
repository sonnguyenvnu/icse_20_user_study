@Override public Description matchExpressionStatement(ExpressionStatementTree tree,VisitorState state){
  ExpressionTree expression=tree.getExpression();
  if (BUILD_CALL.matches(expression,state)) {
    expression=getReceiver(expression);
  }
  if (expression == null) {
    return NO_MATCH;
  }
  if (!FLUENT_SETTER.matches(expression,state)) {
    return NO_MATCH;
  }
  if (!newFluentChain(expression,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Modifying a Builder without assigning it to anything does nothing.").build();
}

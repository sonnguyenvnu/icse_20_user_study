public static Matcher<ExpressionTree> booleanLiteral(boolean value){
  return (expressionTree,state) -> expressionTree.getKind() == Kind.BOOLEAN_LITERAL && value == (Boolean)(((LiteralTree)expressionTree).getValue());
}

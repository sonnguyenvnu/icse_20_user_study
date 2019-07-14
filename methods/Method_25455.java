@Override public boolean matches(StatementTree expressionTree,VisitorState state){
  if (!(expressionTree instanceof ReturnTree)) {
    return false;
  }
  return returnedMatcher.matches(((ReturnTree)expressionTree).getExpression(),state);
}

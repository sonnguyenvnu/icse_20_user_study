@Override public boolean matches(StatementTree expressionTree,VisitorState state){
  if (!(expressionTree instanceof ThrowTree)) {
    return false;
  }
  return thrownMatcher.matches(((ThrowTree)expressionTree).getExpression(),state);
}

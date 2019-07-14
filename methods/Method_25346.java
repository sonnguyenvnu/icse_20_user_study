@Override public boolean matches(StatementTree statementTree,VisitorState state){
  if (!(statementTree instanceof AssertTree)) {
    return false;
  }
  return expressionMatcher.matches(((AssertTree)statementTree).getCondition(),state);
}

private static MatchResult tryTreeMatches(TryTree tryTree,VisitorState state){
  BlockTree tryBlock=tryTree.getBlock();
  List<? extends StatementTree> statements=tryBlock.getStatements();
  if (statements.isEmpty()) {
    return doesNotMatch();
  }
  StatementTree failStatement=null;
  for (  StatementTree statement : statements) {
    if (!(statement instanceof ExpressionStatementTree)) {
      continue;
    }
    if (failOrAssert.matches(((ExpressionStatementTree)statement).getExpression(),state)) {
      failStatement=statement;
      break;
    }
  }
  if (failStatement == null) {
    return doesNotMatch();
  }
  List<? extends CatchTree> catches=tryTree.getCatches();
  if (catches.size() != 1) {
    return doesNotMatch();
  }
  CatchTree catchTree=catches.get(0);
  VariableTree catchType=catchTree.getParameter();
  boolean catchesThrowable=javaLangThrowable.matches(catchType,state);
  boolean catchesError=javaLangError.matches(catchType,state);
  boolean catchesOtherError=someAssertionFailure.matches(catchType,state);
  if (!catchesThrowable && !catchesError && !catchesOtherError) {
    return doesNotMatch();
  }
  List<? extends StatementTree> catchStatements=catchTree.getBlock().getStatements();
  for (  StatementTree catchStatement : catchStatements) {
    if (!Matchers.<Tree>kindIs(EMPTY_STATEMENT).matches(catchStatement,state)) {
      return doesNotMatch();
    }
  }
  return matches(failStatement,catchesThrowable ? JAVA_LANG_THROWABLE : catchesError ? JAVA_LANG_ERROR : SOME_ASSERTION_FAILURE);
}

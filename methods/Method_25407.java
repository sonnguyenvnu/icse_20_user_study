/** 
 * Matches an  {@link ExpressionStatementTree} based on its {@link ExpressionTree}. 
 */
public static Matcher<StatementTree> expressionStatement(Matcher<ExpressionTree> matcher){
  return (statementTree,state) -> statementTree instanceof ExpressionStatementTree && matcher.matches(((ExpressionStatementTree)statementTree).getExpression(),state);
}

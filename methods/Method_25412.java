/** 
 * Matches an enhanced for loop if all the given matchers match.
 * @param variableMatcher The matcher to apply to the variable.
 * @param expressionMatcher The matcher to apply to the expression.
 * @param statementMatcher The matcher to apply to the statement.
 */
public static Matcher<EnhancedForLoopTree> enhancedForLoop(Matcher<VariableTree> variableMatcher,Matcher<ExpressionTree> expressionMatcher,Matcher<StatementTree> statementMatcher){
  return (t,state) -> variableMatcher.matches(t.getVariable(),state) && expressionMatcher.matches(t.getExpression(),state) && statementMatcher.matches(t.getStatement(),state);
}

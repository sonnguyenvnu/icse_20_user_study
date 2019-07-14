/** 
 * Matches an assignment operator AST node if both of the given matchers match.
 * @param variableMatcher The matcher to apply to the variable.
 * @param expressionMatcher The matcher to apply to the expression.
 */
public static Matcher<AssignmentTree> assignment(Matcher<ExpressionTree> variableMatcher,Matcher<? super ExpressionTree> expressionMatcher){
  return (t,state) -> variableMatcher.matches(t.getVariable(),state) && expressionMatcher.matches(t.getExpression(),state);
}

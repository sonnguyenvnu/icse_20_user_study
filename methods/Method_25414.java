/** 
 * Matches a type cast AST node if both of the given matchers match.
 * @param typeMatcher The matcher to apply to the type.
 * @param expressionMatcher The matcher to apply to the expression.
 */
public static Matcher<TypeCastTree> typeCast(Matcher<Tree> typeMatcher,Matcher<ExpressionTree> expressionMatcher){
  return (t,state) -> typeMatcher.matches(t.getType(),state) && expressionMatcher.matches(t.getExpression(),state);
}

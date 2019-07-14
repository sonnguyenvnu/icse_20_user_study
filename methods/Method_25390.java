/** 
 * Ignores any number of parenthesis wrapping an expression and then applies the passed matcher to that expression. For example, the passed matcher would be applied to  {@code value} in {@code (((value)))}.
 */
public static Matcher<ExpressionTree> ignoreParens(Matcher<ExpressionTree> innerMatcher){
  return (tree,state) -> innerMatcher.matches(stripParentheses(tree),state);
}

/** 
 * Matches an AST node that represents a non-static field. 
 */
public static Matcher<ExpressionTree> isInstanceField(){
  return symbolMatcher((symbol,state) -> symbol.getKind() == ElementKind.FIELD && !symbol.isStatic());
}

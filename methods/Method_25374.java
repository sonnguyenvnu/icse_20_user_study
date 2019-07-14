/** 
 * Matches an AST node that represents a local variable or parameter. 
 */
public static Matcher<ExpressionTree> isVariable(){
  return symbolMatcher((symbol,state) -> symbol.getKind() == ElementKind.LOCAL_VARIABLE || symbol.getKind() == ElementKind.PARAMETER);
}

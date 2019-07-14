/** 
 * Matches an AST node which is an expression yielding the indicated static field access. 
 */
public static Matcher<ExpressionTree> staticFieldAccess(){
  return allOf(isStatic(),isSymbol(VarSymbol.class));
}

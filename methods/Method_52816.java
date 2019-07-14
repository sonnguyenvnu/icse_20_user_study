/** 
 * Calculate the boolean complexity of the given expression. NPath boolean complexity is the sum of &amp;&amp; and || tokens. This is calculated by summing the number of children of the &amp;&amp;'s (minus one) and the children of the ||'s (minus one). <p>Note that this calculation applies to Cyclomatic Complexity as well.</p>
 * @param expr control structure expression
 * @return complexity of the boolean expression
 */
public static int sumExpressionComplexity(ASTExpression expr){
  LOGGER.entering(CLASS_NAME,"visit(ASTExpression)");
  if (expr == null) {
    LOGGER.exiting(CLASS_NAME,"visit(ASTExpression)",0);
    return 0;
  }
  List<ASTConditionalAndExpression> andNodes=expr.findDescendantsOfType(ASTConditionalAndExpression.class);
  List<ASTConditionalOrExpression> orNodes=expr.findDescendantsOfType(ASTConditionalOrExpression.class);
  int children=0;
  for (  ASTConditionalOrExpression element : orNodes) {
    children+=element.jjtGetNumChildren();
    children--;
  }
  for (  ASTConditionalAndExpression element : andNodes) {
    children+=element.jjtGetNumChildren();
    children--;
  }
  LOGGER.exiting(CLASS_NAME,"visit(ASTExpression)",children);
  return children;
}

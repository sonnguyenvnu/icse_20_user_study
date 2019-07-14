/** 
 * Evaluates the number of paths through a boolean expression. This is the total number of  {@code &&} and {@code ||}operators appearing in the expression. This is used in the calculation of cyclomatic and n-path complexity.
 * @param expr Expression to analyse
 * @return The number of paths through the expression
 */
public static int booleanExpressionComplexity(Node expr){
  if (expr == null) {
    return 0;
  }
  List<ASTConditionalAndExpression> andNodes=expr.findDescendantsOfType(ASTConditionalAndExpression.class);
  List<ASTConditionalOrExpression> orNodes=expr.findDescendantsOfType(ASTConditionalOrExpression.class);
  int complexity=0;
  if (expr instanceof ASTConditionalOrExpression || expr instanceof ASTConditionalAndExpression) {
    complexity++;
  }
  for (  ASTConditionalOrExpression element : orNodes) {
    complexity+=element.jjtGetNumChildren() - 1;
  }
  for (  ASTConditionalAndExpression element : andNodes) {
    complexity+=element.jjtGetNumChildren() - 1;
  }
  return complexity;
}

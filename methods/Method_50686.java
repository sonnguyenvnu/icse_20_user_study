/** 
 * Computes the number of control flow paths through that expression, which is the number of  {@code ||} and {@code &&} operators. Used both by Npath and Cyclo.
 * @param expression Boolean expression
 * @return The complexity of the expression
 */
public static int booleanExpressionComplexity(ASTStandardCondition expression){
  Set<ASTBooleanExpression> subs=new HashSet<>(expression.findDescendantsOfType(ASTBooleanExpression.class));
  int complexity=0;
  for (  ASTBooleanExpression sub : subs) {
    BooleanOp op=sub.getOperator();
    if (op != null && (op == BooleanOp.AND || op == BooleanOp.OR)) {
      complexity++;
    }
  }
  return complexity;
}

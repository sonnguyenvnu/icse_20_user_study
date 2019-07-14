/** 
 * Returns the node that represents the expression that will be evaluated if the guard evaluates to true.
 */
public ASTExpression getTrueAlternative(){
  return (ASTExpression)jjtGetChild(1);
}

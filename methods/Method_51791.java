/** 
 * Returns the expression that corresponds to the detail message, i.e. the expression after the colon, if it's present.
 */
public ASTExpression getDetailMessageNode(){
  return hasDetailMessage() ? (ASTExpression)jjtGetChild(1) : null;
}

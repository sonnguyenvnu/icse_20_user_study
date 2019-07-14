/** 
 * Returns the node that represents the guard of this loop. This may be any expression of type boolean. <p>If this node represents a foreach loop, or if there is no specified guard, then returns null.
 */
public ASTExpression getGuardExpressionNode(){
  if (isForeach()) {
    return null;
  }
  return getFirstChildOfType(ASTExpression.class);
}

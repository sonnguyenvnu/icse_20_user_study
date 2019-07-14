/** 
 * Tells if this BlockStatement is an allocation statement. This is done by
 * @return the result ofcontainsDescendantOfType(ASTAllocationExpression.class)
 */
public final boolean isAllocation(){
  return hasDescendantOfType(ASTAllocationExpression.class);
}

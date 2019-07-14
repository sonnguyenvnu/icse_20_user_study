/** 
 * Indicate whether this node is allocating a new object.
 * @param n node that might be allocating a new object
 * @return true if child 0 is an AllocationExpression
 */
private boolean isAllocation(Node n){
  return n.jjtGetNumChildren() > 0 && n.jjtGetChild(0) instanceof ASTAllocationExpression && n.jjtGetParent().jjtGetNumChildren() == 1;
}

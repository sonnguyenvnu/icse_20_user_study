private boolean isCompoundAssignment(Node primaryExpression){
  return ((ASTAssignmentOperator)primaryExpression.jjtGetChild(1)).isCompound();
}

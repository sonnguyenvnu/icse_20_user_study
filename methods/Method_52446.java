private boolean hasAssignmentOperator(Node node){
  if (node instanceof ASTStatementExpression || node instanceof ASTExpression) {
    if (node.jjtGetNumChildren() >= 2 && node.jjtGetChild(1) instanceof ASTAssignmentOperator) {
      return true;
    }
  }
  return false;
}

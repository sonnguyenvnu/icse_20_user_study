private boolean isInAssignment(Node potentialStatement){
  if (potentialStatement instanceof ASTStatementExpression) {
    ASTStatementExpression statement=(ASTStatementExpression)potentialStatement;
    List<ASTAssignmentOperator> assignments=statement.findDescendantsOfType(ASTAssignmentOperator.class);
    return !assignments.isEmpty() && "=".equals(assignments.get(0).getImage());
  }
 else {
    return false;
  }
}

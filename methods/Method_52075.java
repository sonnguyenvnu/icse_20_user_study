/** 
 * Checks if the variable designed in parameter is written to a field (not local variable) in the statements.
 */
private boolean checkForDirectAssignment(Object ctx,final ASTFormalParameter parameter,final List<ASTBlockStatement> bs){
  final ASTVariableDeclaratorId vid=parameter.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
  final String varName=vid.getImage();
  for (  ASTBlockStatement b : bs) {
    if (b.hasDescendantOfType(ASTAssignmentOperator.class)) {
      final ASTStatementExpression se=b.getFirstDescendantOfType(ASTStatementExpression.class);
      if (se == null || !(se.jjtGetChild(0) instanceof ASTPrimaryExpression)) {
        continue;
      }
      String assignedVar=getExpressionVarName(se);
      if (assignedVar == null) {
        continue;
      }
      ASTPrimaryExpression pe=(ASTPrimaryExpression)se.jjtGetChild(0);
      Node n=pe.getFirstParentOfType(ASTMethodDeclaration.class);
      if (n == null) {
        n=pe.getFirstParentOfType(ASTConstructorDeclaration.class);
        if (n == null) {
          continue;
        }
      }
      if (!isLocalVariable(assignedVar,n)) {
        if (se.jjtGetNumChildren() < 3) {
          continue;
        }
        ASTExpression e=(ASTExpression)se.jjtGetChild(2);
        if (e.hasDescendantOfType(ASTEqualityExpression.class)) {
          continue;
        }
        String val=getExpressionVarName(e);
        if (val == null) {
          continue;
        }
        ASTPrimarySuffix foo=e.getFirstDescendantOfType(ASTPrimarySuffix.class);
        if (foo != null && foo.isArrayDereference()) {
          continue;
        }
        if (val.equals(varName)) {
          Node md=parameter.getFirstParentOfType(ASTMethodDeclaration.class);
          if (md == null) {
            md=pe.getFirstParentOfType(ASTConstructorDeclaration.class);
          }
          if (!isLocalVariable(varName,md)) {
            addViolation(ctx,parameter,varName);
          }
        }
      }
    }
  }
  return false;
}

@Override public Object visit(ASTVariableDeclaratorId node,Object data){
  if (!TypeHelper.isA(node,String.class) || node.isArray()) {
    return data;
  }
  Node parent=node.jjtGetParent().jjtGetParent();
  if (!(parent instanceof ASTLocalVariableDeclaration)) {
    return data;
  }
  for (  NameOccurrence no : node.getUsages()) {
    Node name=no.getLocation();
    ASTStatementExpression statement=name.getFirstParentOfType(ASTStatementExpression.class);
    if (statement == null) {
      continue;
    }
    ASTArgumentList argList=name.getFirstParentOfType(ASTArgumentList.class);
    if (argList != null && argList.getFirstParentOfType(ASTStatementExpression.class) == statement) {
      continue;
    }
    ASTEqualityExpression equality=name.getFirstParentOfType(ASTEqualityExpression.class);
    if (equality != null && equality.getFirstParentOfType(ASTStatementExpression.class) == statement) {
      continue;
    }
    ASTConditionalExpression conditional=name.getFirstParentOfType(ASTConditionalExpression.class);
    if (conditional != null) {
      Node thirdParent=name.getNthParent(3);
      Node fourthParent=name.getNthParent(4);
      if ((Objects.equals(thirdParent,conditional) || Objects.equals(fourthParent,conditional)) && conditional.getFirstParentOfType(ASTStatementExpression.class) == statement) {
        continue;
      }
    }
    if (statement.jjtGetNumChildren() > 0 && statement.jjtGetChild(0) instanceof ASTPrimaryExpression) {
      ASTName astName=statement.jjtGetChild(0).getFirstDescendantOfType(ASTName.class);
      if (astName != null) {
        if (astName.equals(name)) {
          ASTAssignmentOperator assignmentOperator=statement.getFirstDescendantOfType(ASTAssignmentOperator.class);
          if (assignmentOperator != null && assignmentOperator.isCompound()) {
            addViolation(data,assignmentOperator);
          }
        }
 else         if (astName.getImage().equals(name.getImage())) {
          ASTAssignmentOperator assignmentOperator=statement.getFirstDescendantOfType(ASTAssignmentOperator.class);
          if (assignmentOperator != null && !assignmentOperator.isCompound()) {
            addViolation(data,astName);
          }
        }
      }
    }
  }
  return data;
}

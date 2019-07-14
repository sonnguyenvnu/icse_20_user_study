private boolean checkLocalVariableUsage(ASTMethodDeclaration node,String returnVariableName){
  List<ASTLocalVariableDeclaration> locals=node.findDescendantsOfType(ASTLocalVariableDeclaration.class);
  ASTVariableInitializer initializer=null;
  for (  ASTLocalVariableDeclaration l : locals) {
    ASTVariableDeclaratorId id=l.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
    if (id != null && id.hasImageEqualTo(returnVariableName)) {
      initializer=l.getFirstDescendantOfType(ASTVariableInitializer.class);
      break;
    }
  }
  if (initializer == null) {
    return false;
  }
  if (initializer.jjtGetNumChildren() > 0 && initializer.jjtGetChild(0) instanceof ASTExpression && initializer.jjtGetChild(0).jjtGetNumChildren() > 0 && initializer.jjtGetChild(0).jjtGetChild(0) instanceof ASTPrimaryExpression && initializer.jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() > 0 && initializer.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0) instanceof ASTPrimaryPrefix && initializer.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetNumChildren() > 0 && initializer.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0) instanceof ASTName) {
    ASTName name=(ASTName)initializer.jjtGetChild(0).jjtGetChild(0).jjtGetChild(0).jjtGetChild(0);
    if (name == null || !volatileFields.contains(name.getImage())) {
      return false;
    }
  }
 else {
    return false;
  }
  List<ASTName> names=node.findDescendantsOfType(ASTName.class);
  for (  ASTName n : names) {
    if (!n.hasImageEqualTo(returnVariableName)) {
      continue;
    }
    Node expression=n.getNthParent(3);
    if (expression instanceof ASTEqualityExpression) {
      continue;
    }
    if (expression instanceof ASTStatementExpression) {
      if (expression.jjtGetNumChildren() > 2 && expression.jjtGetChild(1) instanceof ASTAssignmentOperator) {
        ASTName value=expression.jjtGetChild(2).getFirstDescendantOfType(ASTName.class);
        if (value == null || !volatileFields.contains(value.getImage())) {
          return false;
        }
      }
    }
  }
  return true;
}

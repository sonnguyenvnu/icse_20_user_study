@Override public Object visit(ASTUserClass node,Object data){
  if (Helper.isTestMethodOrClass(node) || Helper.isSystemLevelClass(node)) {
    return data;
  }
  List<ASTAssignmentExpression> assignmentExprs=node.findDescendantsOfType(ASTAssignmentExpression.class);
  for (  ASTAssignmentExpression assignment : assignmentExprs) {
    findSafeLiterals(assignment);
  }
  List<ASTVariableDeclaration> variableDecls=node.findDescendantsOfType(ASTVariableDeclaration.class);
  for (  ASTVariableDeclaration varDecl : variableDecls) {
    findSafeLiterals(varDecl);
  }
  List<ASTField> fieldDecl=node.findDescendantsOfType(ASTField.class);
  for (  ASTField fDecl : fieldDecl) {
    findSafeLiterals(fDecl);
  }
  List<ASTNewObjectExpression> newObjects=node.findDescendantsOfType(ASTNewObjectExpression.class);
  for (  ASTNewObjectExpression newObj : newObjects) {
    checkNewObjects(newObj,data);
  }
  listOfStringLiteralVariables.clear();
  return data;
}

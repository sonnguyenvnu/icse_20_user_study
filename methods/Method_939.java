public static void addViolationWithPrecisePosition(AbstractRule rule,Node node,Object data,String message){
  if (node instanceof ASTFieldDeclaration) {
    ASTVariableDeclaratorId variableDeclaratorId=node.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
    addViolation(rule,variableDeclaratorId,data,message);
    return;
  }
  if (node instanceof ASTMethodDeclaration) {
    ASTMethodDeclarator declarator=node.getFirstChildOfType(ASTMethodDeclarator.class);
    addViolation(rule,declarator,data,message);
    return;
  }
  addViolation(rule,node,data,message);
}

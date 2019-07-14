@Override public Object visit(ASTMethodDeclaration node,Object data){
  boolean violation=false;
  String localVarName=null;
  String returnVariableName=null;
  if (node.getResultType().isVoid()) {
    return super.visit(node,data);
  }
  if ("getInstance".equals(node.getMethodName())) {
    List<ASTReturnStatement> rsl=node.findDescendantsOfType(ASTReturnStatement.class);
    if (rsl.isEmpty()) {
      return super.visit(node,data);
    }
 else {
      for (      ASTReturnStatement rs : rsl) {
        List<ASTPrimaryExpression> pel=rs.findDescendantsOfType(ASTPrimaryExpression.class);
        ASTPrimaryExpression ape=pel.get(0);
        if (ape.getFirstDescendantOfType(ASTAllocationExpression.class) != null) {
          violation=true;
          break;
        }
      }
    }
    List<ASTBlockStatement> astBlockStatements=node.findDescendantsOfType(ASTBlockStatement.class);
    returnVariableName=getReturnVariableName(node);
    if (!astBlockStatements.isEmpty()) {
      for (      ASTBlockStatement blockStatement : astBlockStatements) {
        if (blockStatement.hasDescendantOfType(ASTLocalVariableDeclaration.class)) {
          List<ASTLocalVariableDeclaration> lVarList=blockStatement.findDescendantsOfType(ASTLocalVariableDeclaration.class);
          if (!lVarList.isEmpty()) {
            for (            ASTLocalVariableDeclaration localVar : lVarList) {
              for (              ASTVariableDeclaratorId id : localVar) {
                localVarName=id.getVariableName();
                if (returnVariableName != null && returnVariableName.equals(localVarName)) {
                  violation=true;
                  break;
                }
              }
            }
          }
        }
      }
    }
  }
  if (violation) {
    addViolation(data,node);
  }
  return super.visit(node,data);
}

private void checkForResources(Node node,Object data){
  List<ASTLocalVariableDeclaration> localVars=node.findDescendantsOfType(ASTLocalVariableDeclaration.class);
  List<ASTVariableDeclarator> vars=new ArrayList<>();
  Map<ASTVariableDeclaratorId,TypeNode> ids=new HashMap<>();
  for (  ASTLocalVariableDeclaration localVar : localVars) {
    vars.addAll(localVar.findChildrenOfType(ASTVariableDeclarator.class));
  }
  for (  ASTVariableDeclarator var : vars) {
    TypeNode type=((ASTLocalVariableDeclaration)var.jjtGetParent()).getTypeNode();
    if (type != null && isResourceTypeOrSubtype(type)) {
      if (var.hasInitializer()) {
        ASTExpression expression=var.getInitializer().getFirstChildOfType(ASTExpression.class);
        TypeNode runtimeType=expression;
        if (!isMethodCall(expression) && runtimeType != null && runtimeType.getType() != null) {
          type=runtimeType;
        }
        ASTExpression firstArgument=getAllocationFirstArgument(expression);
        if (firstArgument != null) {
          type=firstArgument;
        }
      }
      if (!isAllowedResourceType(type)) {
        ids.put(var.getVariableId(),type);
      }
    }
  }
  for (  Map.Entry<ASTVariableDeclaratorId,TypeNode> entry : ids.entrySet()) {
    ASTVariableDeclaratorId variableId=entry.getKey();
    ensureClosed((ASTLocalVariableDeclaration)variableId.jjtGetParent().jjtGetParent(),variableId,entry.getValue(),data);
  }
}

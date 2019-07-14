@Override public Object visit(ASTFieldDeclaration node,Object data){
  if (!node.isStatic()) {
    return data;
  }
  ASTClassOrInterfaceType cit=node.getFirstDescendantOfType(ASTClassOrInterfaceType.class);
  if (cit == null || !TypeHelper.isA(cit,formatterClassToCheck)) {
    return data;
  }
  ASTVariableDeclaratorId var=node.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
  for (  String formatter : THREAD_SAFE_FORMATTER) {
    if (TypeHelper.isA(var,formatter)) {
      return data;
    }
  }
  for (  NameOccurrence occ : var.getUsages()) {
    Node n=occ.getLocation();
    if (!n.getImage().contains(".")) {
      continue;
    }
    if (getProperty(ALLOW_METHOD_LEVEL_SYNC)) {
      ASTMethodDeclaration method=n.getFirstParentOfType(ASTMethodDeclaration.class);
      if (method != null && (!method.isSynchronized() || !method.isStatic())) {
        addViolation(data,node);
      }
      continue;
    }
    ASTSynchronizedStatement syncStatement=n.getFirstParentOfType(ASTSynchronizedStatement.class);
    if (syncStatement != null) {
      ASTExpression expression=syncStatement.getFirstChildOfType(ASTExpression.class);
      if (expression != null) {
        ASTName name=expression.getFirstDescendantOfType(ASTName.class);
        if (name != null && name.hasImageEqualTo(var.getVariableName())) {
          continue;
        }
      }
    }
    addViolation(data,n);
  }
  return data;
}

static public Type extracTypeInfo2(ASTNode node){
  Messages.log("* extracTypeInfo2");
  if (node == null)   return null;
switch (node.getNodeType()) {
case ASTNode.METHOD_DECLARATION:
    return ((MethodDeclaration)node).getReturnType2();
case ASTNode.FIELD_DECLARATION:
  return ((FieldDeclaration)node).getType();
case ASTNode.VARIABLE_DECLARATION_EXPRESSION:
return ((VariableDeclarationExpression)node).getType();
case ASTNode.VARIABLE_DECLARATION_STATEMENT:
return ((VariableDeclarationStatement)node).getType();
case ASTNode.SINGLE_VARIABLE_DECLARATION:
return ((SingleVariableDeclaration)node).getType();
case ASTNode.VARIABLE_DECLARATION_FRAGMENT:
return extracTypeInfo2(node.getParent());
}
log("Unknown type info request " + getNodeAsString(node));
return null;
}

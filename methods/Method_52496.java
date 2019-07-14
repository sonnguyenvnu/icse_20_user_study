private void populateVariableDeclaratorFromType(ASTLocalVariableDeclaration node,JavaTypeDefinition typeDefinition){
  TypeNode var=node.getFirstChildOfType(ASTVariableDeclarator.class);
  if (var != null) {
    var.setTypeDefinition(typeDefinition);
    var=var.getFirstChildOfType(ASTVariableDeclaratorId.class);
  }
  if (var != null) {
    var.setTypeDefinition(typeDefinition);
  }
}

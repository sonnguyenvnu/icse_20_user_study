public static String getVariableName(AbstractJavaAccessNode typeNode){
  ASTVariableDeclaratorId decl=typeNode.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
  if (decl != null) {
    return decl.getImage();
  }
  return null;
}

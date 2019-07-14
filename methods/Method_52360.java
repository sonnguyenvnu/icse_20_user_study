/** 
 * This method checks the variable declaration if it is on a class we care about. If it is, it returns the DeclaratorId
 * @param node The ASTLocalVariableDeclaration which is a problem
 * @return ASTVariableDeclaratorId
 */
private ASTVariableDeclaratorId getDeclaration(ASTLocalVariableDeclaration node){
  ASTType type=node.getTypeNode();
  if (type != null) {
    if (MAP_CLASSES.keySet().contains(type.getTypeImage())) {
      return node.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
    }
  }
  return null;
}

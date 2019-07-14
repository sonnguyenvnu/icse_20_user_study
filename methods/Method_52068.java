/** 
 * TODO modify usages to use symbol table Tells if the variable name is a local variable declared in the method.
 * @param vn the variable name
 * @param node the ASTMethodDeclaration where the local variable name will be searched
 * @return <code>true</code> if the method declaration contains any localvariable named vn and <code>false</code> in other case
 */
protected boolean isLocalVariable(String vn,Node node){
  final List<ASTLocalVariableDeclaration> lvars=node.findDescendantsOfType(ASTLocalVariableDeclaration.class);
  if (lvars != null) {
    for (    ASTLocalVariableDeclaration lvd : lvars) {
      final ASTVariableDeclaratorId vid=lvd.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
      if (vid != null && vid.hasImageEqualTo(vn)) {
        return true;
      }
    }
  }
  return false;
}

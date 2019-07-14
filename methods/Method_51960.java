/** 
 * Gets a list of all fields declared in the class.
 * @param node The class
 * @return The list of all fields
 */
protected List<ASTFieldDeclaration> getFields(ASTAnyTypeDeclaration node){
  return getDeclarationsOfType(node,ASTFieldDeclaration.class);
}

/** 
 * Gets a list of all methods and constructors declared in the class.
 * @param node The class
 * @return The list of all methods and constructors
 */
protected List<ASTMethodOrConstructorDeclaration> getMethodsAndConstructors(ASTAnyTypeDeclaration node){
  return getDeclarationsOfType(node,ASTMethodOrConstructorDeclaration.class);
}

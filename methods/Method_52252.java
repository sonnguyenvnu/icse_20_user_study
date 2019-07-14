/** 
 * Checks if the given exception is declared in the method or constructor signature.
 * @param exception to evaluate
 * @return true if parent node is either a method or constructor declaration
 */
private boolean isParentSignatureDeclaration(ASTName exception){
  Node parent=exception.jjtGetParent().jjtGetParent();
  return parent instanceof ASTMethodDeclaration || parent instanceof ASTConstructorDeclaration;
}

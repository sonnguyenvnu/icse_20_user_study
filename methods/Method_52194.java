/** 
 * Returns  {@code true} if access modifier of construtor is same as class's,otherwise  {@code false}.
 * @param node the class declaration node
 * @param cons the constructor declaration node
 */
private boolean haveSameAccessModifier(ASTClassOrInterfaceDeclaration node,ASTConstructorDeclaration cons){
  return node.isPrivate() && cons.isPrivate() || node.isProtected() && cons.isProtected() || node.isPublic() && cons.isPublic() || node.isPackagePrivate() && cons.isPackagePrivate();
}

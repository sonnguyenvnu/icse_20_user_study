/** 
 * Returns  {@code true} if the node has only one {@link ASTConstructorDeclaration}child node and the constructor has empty body or simply invokes the superclass constructor with no arguments.
 * @param node the node to check
 */
private boolean isExplicitDefaultConstructor(Node node){
  List<ASTConstructorDeclaration> nodes=node.findDescendantsOfType(ASTConstructorDeclaration.class);
  if (nodes.size() != 1) {
    return false;
  }
  ASTConstructorDeclaration cdnode=nodes.get(0);
  return cdnode.getParameterCount() == 0 && !hasIgnoredAnnotation(cdnode) && !cdnode.hasDescendantOfType(ASTBlockStatement.class) && !cdnode.hasDescendantOfType(ASTNameList.class) && hasDefaultConstructorInvocation(cdnode);
}

/** 
 * Returns the the first Class declaration around the node.
 * @param node The node with the enclosing Class declaration.
 * @return The JavaTypeDefinition of the enclosing Class declaration.
 */
private TypeNode getEnclosingTypeDeclaration(Node node){
  Node previousNode=null;
  while (node != null) {
    if (node instanceof ASTClassOrInterfaceDeclaration) {
      return (TypeNode)node;
    }
 else     if (node instanceof ASTAllocationExpression && node.getFirstChildOfType(ASTArrayDimsAndInits.class) == null && !(previousNode instanceof ASTArguments)) {
      return (TypeNode)node;
    }
    previousNode=node;
    node=node.jjtGetParent();
  }
  return null;
}

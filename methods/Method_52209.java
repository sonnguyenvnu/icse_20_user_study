private String getPrintableNodeKind(Node node){
  if (node instanceof ASTAnyTypeDeclaration) {
    return ((ASTAnyTypeDeclaration)node).getTypeKind().getPrintableName();
  }
 else   if (node instanceof MethodLikeNode) {
    return ((MethodLikeNode)node).getKind().getPrintableName();
  }
 else   if (node instanceof ASTFieldDeclaration) {
    return "field";
  }
 else   if (node instanceof ASTResource) {
    return "resource specification";
  }
  throw new UnsupportedOperationException("Node " + node + " is unaccounted for");
}

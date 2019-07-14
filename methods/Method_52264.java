private <T>void lclFindChildrenOfType(Node node,Class<T> targetType,List<T> results){
  if (node.getClass().equals(targetType)) {
    results.add((T)node);
  }
  if (node instanceof ASTClassOrInterfaceDeclaration && ((ASTClassOrInterfaceDeclaration)node).isNested()) {
    return;
  }
  if (node instanceof ASTClassOrInterfaceBodyDeclaration && ((ASTClassOrInterfaceBodyDeclaration)node).isAnonymousInnerClass()) {
    return;
  }
  for (int i=0; i < node.jjtGetNumChildren(); i++) {
    Node child=node.jjtGetChild(i);
    if (child.getClass().equals(targetType)) {
      results.add((T)child);
    }
  }
}

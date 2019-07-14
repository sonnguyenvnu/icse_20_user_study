private void checkDeclarationInInterfaceType(Object data,Node fieldOrMethod,Set<Modifier> unnecessary){
  Node parent=fieldOrMethod.jjtGetParent().jjtGetParent().jjtGetParent();
  if (parent instanceof ASTAnnotationTypeDeclaration || parent instanceof ASTClassOrInterfaceDeclaration && ((ASTClassOrInterfaceDeclaration)parent).isInterface()) {
    reportUnnecessaryModifiers(data,fieldOrMethod,unnecessary,"the " + getPrintableNodeKind(fieldOrMethod) + " is declared in an " + getPrintableNodeKind(parent) + " type");
  }
}

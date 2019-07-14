private DeclarationKind determineKind(){
  if (jjtGetNumChildren() == 0) {
    return EMPTY;
  }
  JavaNode node=getDeclarationNode();
  if (node instanceof ASTInitializer) {
    return INITIALIZER;
  }
 else   if (node instanceof ASTConstructorDeclaration) {
    return CONSTRUCTOR;
  }
 else   if (node instanceof ASTMethodDeclaration) {
    return METHOD;
  }
 else   if (node instanceof ASTAnnotationMethodDeclaration) {
    return ANNOTATION_METHOD;
  }
 else   if (node instanceof ASTFieldDeclaration) {
    return FIELD;
  }
 else   if (node instanceof ASTClassOrInterfaceDeclaration) {
    return ((ASTClassOrInterfaceDeclaration)node).isInterface() ? INTERFACE : CLASS;
  }
 else   if (node instanceof ASTAnnotationTypeDeclaration) {
    return ANNOTATION;
  }
 else   if (node instanceof ASTEnumDeclaration) {
    return ENUM;
  }
  throw new IllegalStateException("Declaration node types should all be known");
}

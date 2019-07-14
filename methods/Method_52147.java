private boolean shouldReport(final AccessNode decl){
  final ASTAnyTypeDeclaration parentClassOrInterface=decl.getFirstParentOfType(ASTAnyTypeDeclaration.class);
  boolean isConcreteClass=parentClassOrInterface.getTypeKind() == ASTAnyTypeDeclaration.TypeKind.CLASS;
  return isConcreteClass && isMissingComment(decl);
}

private boolean shouldReportTypeDeclaration(ASTAnyTypeDeclaration decl){
  return decl.getTypeKind() != ASTAnyTypeDeclaration.TypeKind.INTERFACE && isMissingComment(decl) && (decl.isNested() || getProperty(TOP_LEVEL_TYPES));
}

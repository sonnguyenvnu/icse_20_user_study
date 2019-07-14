public static UClassIdent create(ClassSymbol sym){
  return create(ASTHelpers.outermostClass(sym).getQualifiedName(),sym.getQualifiedName());
}

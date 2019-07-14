private ASTImportDeclaration findFirstMatch(List<ASTImportDeclaration> imports){
  ASTImportDeclaration result=null;
  for (  ASTImportDeclaration importDeclaration : imports) {
    if (importDeclaration.isStatic()) {
      result=importDeclaration;
      break;
    }
  }
  if (result == null) {
    for (    ASTImportDeclaration importDeclaration : imports) {
      if (!importDeclaration.isStatic()) {
        result=importDeclaration;
        break;
      }
    }
  }
  return result;
}

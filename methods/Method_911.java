/** 
 * if there is no introduction of related packages, skip the inspection The import statement is generally at the start of the class, do some cleaning
 */
private boolean hasSqlMapClientImport(List<ASTImportDeclaration> importDeclarations){
  if (importDeclarations == null || importDeclarations.isEmpty()) {
    return false;
  }
  for (  ASTImportDeclaration importDeclaration : importDeclarations) {
    ASTName astName=importDeclaration.getFirstChildOfType(ASTName.class);
    boolean hasImport=astName != null && (SQL_MAP_CLIENT_IMPORT_FULL_NAME.equals(astName.getImage()) || SQL_MAP_CLIENT_IMPORT_SIMPLE_NAME.equals(astName.getImage()));
    if (hasImport) {
      return true;
    }
  }
  return false;
}

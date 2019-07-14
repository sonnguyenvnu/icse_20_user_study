/** 
 * Configures the type resolution for the symbol table.
 * @param imports the import declarations
 */
public void configureImports(final List<ASTImportDeclaration> imports){
  for (  ASTImportDeclaration i : imports) {
    if (i.isImportOnDemand()) {
      types.addImport(i.getImportedName() + ".*");
    }
 else {
      types.addImport(i.getImportedName());
    }
  }
}

/** 
 * Returns true if the name could be imported by this declaration. The name must be fully qualified, the import is either on-demand or static, that is its  {@link ASTImportDeclaration#getImportedName()}is the enclosing package or type name of the imported type or static member.
 */
private boolean declarationMatches(ASTImportDeclaration decl,String name){
  return name.startsWith(decl.getImportedName()) && name.lastIndexOf('.') == decl.getImportedName().length();
}

/** 
 * Tells if the type declaration has a field with varName.
 * @param varName the name of the field to search
 * @param typeDeclaration the type declaration
 * @return <code>true</code> if there is a field in the type declarationnamed varName, <code>false</code> in other case
 */
protected final boolean isField(String varName,ASTAnyTypeDeclaration typeDeclaration){
  final List<ASTFieldDeclaration> fds=typeDeclaration.findDescendantsOfType(ASTFieldDeclaration.class);
  if (fds != null) {
    for (    ASTFieldDeclaration fd : fds) {
      final ASTVariableDeclaratorId vid=fd.getFirstDescendantOfType(ASTVariableDeclaratorId.class);
      if (vid != null && vid.hasImageEqualTo(varName)) {
        return true;
      }
    }
  }
  return false;
}

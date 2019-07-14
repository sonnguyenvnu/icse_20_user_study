/** 
 * Gets the variable name of this field. This method searches the first VariableDeclartorId node and returns its image or <code>null</code> if the child node is not found.
 * @return a String representing the name of the variable
 * @deprecated FieldDeclaration may declare several variables, so this is not exhaustiveIterate on the  {@linkplain ASTVariableDeclaratorId VariableDeclaratorIds} instead
 */
@Deprecated public String getVariableName(){
  ASTVariableDeclaratorId decl=getFirstDescendantOfType(ASTVariableDeclaratorId.class);
  if (decl != null) {
    return decl.getImage();
  }
  return null;
}

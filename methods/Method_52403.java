/** 
 * Checks if a FieldDeclaration is a reference type (includes arrays). The reference information is in the FieldDeclaration for this example: <pre> int[] ia1 </pre> and in the VariableDeclarator for this example: <pre> int ia2[]; </pre> .
 * @param fieldDeclaration the field to check.
 * @param variableDeclarator the variable declarator to check.
 * @return <code>true</code> if the field is a reference. <code>false</code>otherwise.
 */
private boolean isRef(ASTFieldDeclaration fieldDeclaration,ASTVariableDeclarator variableDeclarator){
  Node type=fieldDeclaration.jjtGetChild(0).jjtGetChild(0);
  if (type instanceof ASTReferenceType) {
    return true;
  }
 else {
    return ((ASTVariableDeclaratorId)variableDeclarator.jjtGetChild(0)).isArray();
  }
}

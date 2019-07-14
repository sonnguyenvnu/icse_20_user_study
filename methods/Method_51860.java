/** 
 * If true, this local variable declaration represents a declaration, which makes use of local variable type inference, e.g. java10 "var". You can receive the inferred type via  {@link ASTVariableDeclarator#getType()}.
 * @see ASTVariableDeclaratorId#isTypeInferred()
 */
public boolean isTypeInferred(){
  return getTypeNode() == null;
}

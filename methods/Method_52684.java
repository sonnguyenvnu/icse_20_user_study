/** 
 * Returns whether this name node is the name of a variable declaration.
 * @return <code>true</code> if name of a variable declaration,<code>false</code> otherwise.
 */
public boolean isVariableDeclaration(){
  return jjtGetParent() instanceof ASTVariableInitializer && ((ASTVariableInitializer)jjtGetParent()).getTarget() == this;
}

/** 
 * Returns true if this node represents a foreach loop.
 */
public boolean isForeach(){
  return jjtGetChild(0) instanceof ASTLocalVariableDeclaration;
}

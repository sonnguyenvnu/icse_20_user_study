/** 
 * Returns the statement that represents the body of this loop.
 */
public ASTStatement getBody(){
  return (ASTStatement)jjtGetChild(jjtGetNumChildren() - 1);
}

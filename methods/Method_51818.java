/** 
 * Returns the statement that will be run while the guard evaluates to true.
 */
public ASTStatement getBody(){
  return (ASTStatement)jjtGetChild(0);
}

/** 
 * Returns the statement that will be run if the guard evaluates to true.
 */
public ASTStatement getThenBranch(){
  return (ASTStatement)jjtGetChild(1);
}

/** 
 * Returns the Block node of this catch branch.
 */
public ASTBlock getBlock(){
  return getFirstChildOfType(ASTBlock.class);
}

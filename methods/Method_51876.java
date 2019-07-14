/** 
 * Returns the block defined by this method, or null if the method is abstract.
 */
public ASTBlock getBlock(){
  return getFirstChildOfType(ASTBlock.class);
}

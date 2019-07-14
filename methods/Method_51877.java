/** 
 * Returns the exception names listed in the  {@code throws} clauseof this method declaration, or null if there are none.
 */
public ASTNameList getThrows(){
  return getFirstChildOfType(ASTNameList.class);
}

/** 
 * Returns the list of type nodes denoting the exception types caught by this catch block. The returned list has at least one element.
 */
public List<ASTType> getCaughtExceptionTypeNodes(){
  return getFirstChildOfType(ASTFormalParameter.class).findChildrenOfType(ASTType.class);
}

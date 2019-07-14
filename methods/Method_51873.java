/** 
 * Returns the simple name of the method.
 */
public String getMethodName(){
  return getFirstChildOfType(ASTMethodDeclarator.class).getImage();
}

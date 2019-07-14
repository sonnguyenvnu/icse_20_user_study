/** 
 * Gets the name of the method.
 * @return a String representing the name of the method
 */
@Override public String getMethodName(){
  ASTMethodDeclarator md=getFirstChildOfType(ASTMethodDeclarator.class);
  if (md != null) {
    return md.getImage();
  }
  return null;
}

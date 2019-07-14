/** 
 * Only to find out whether there is any violation within the scope of the corresponding method
 * @param variableName
 * @param item
 * @return
 * @throws JaxenException
 */
private boolean checkBlockNodesValid(String variableName,Node item){
  if (item instanceof ASTName) {
    String name=item.getImage();
    if (judgeName(name,variableName)) {
      return true;
    }
  }
  return false;
}

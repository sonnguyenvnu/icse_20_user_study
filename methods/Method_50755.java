/** 
 * @param node
 * @param data
 */
private void checkForCSRF(ASTMethod node,Object data){
  if (node.isConstructor()) {
    if (Helper.foundAnyDML(node)) {
      addViolation(data,node);
    }
  }
  String name=node.getImage();
  if (name.equalsIgnoreCase(INIT)) {
    if (Helper.foundAnyDML(node)) {
      addViolation(data,node);
    }
  }
}

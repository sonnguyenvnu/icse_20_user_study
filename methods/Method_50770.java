/** 
 * Does class have sharing keyword declared?
 * @param node
 * @return
 */
private boolean isSharingPresent(ASTUserClass node){
  return node.getModifiers().isWithoutSharing() || node.getModifiers().isWithSharing() || node.getModifiers().isInheritedSharing();
}

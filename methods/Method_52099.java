/** 
 * @return true if this occurence is as an argument to List.get on the correct list 
 */
private boolean occurenceIsListGet(NameOccurrence occ,String listName){
  if (occ.getLocation() instanceof ASTName) {
    ASTPrimarySuffix suffix=occ.getLocation().getFirstParentOfType(ASTPrimarySuffix.class);
    if (suffix == null) {
      return false;
    }
    Node prefix=suffix.jjtGetParent().jjtGetChild(0);
    if (!(prefix instanceof ASTPrimaryPrefix) || prefix.jjtGetNumChildren() != 1 || !(prefix.jjtGetChild(0) instanceof ASTName)) {
      return false;
    }
    String callImage=prefix.jjtGetChild(0).getImage();
    return (listName + ".get").equals(callImage);
  }
  return false;
}

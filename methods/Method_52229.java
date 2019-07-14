/** 
 * Method counts a node if it is public
 * @param node The access node.
 * @return Integer 1 if node is public 0 otherwise
 */
private Integer getTallyOnAccessType(AccessNode node){
  if (node.isPublic()) {
    return NumericConstants.ONE;
  }
  return NumericConstants.ZERO;
}

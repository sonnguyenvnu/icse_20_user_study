/** 
 * @return ????ID
 */
default Set<String> getAllPositionId(){
  return getPositionIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
}

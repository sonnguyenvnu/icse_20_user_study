/** 
 * @return ????ID
 */
default Set<String> getAllOrgId(){
  return getOrgIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
}

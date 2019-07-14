/** 
 * @return ????ID
 */
default Set<String> getAllDistrictId(){
  return getDistrictIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
}

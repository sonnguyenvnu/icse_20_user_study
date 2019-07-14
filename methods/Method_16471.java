/** 
 * @return ????ID
 */
default Set<String> getAllDepartmentId(){
  return getDepartmentIds().stream().map(TreeNode::getAllValue).flatMap(List::stream).collect(Collectors.toSet());
}

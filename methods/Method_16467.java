/** 
 * @return ???ID
 */
default Set<String> getRootDepartmentId(){
  return getDepartmentIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
}

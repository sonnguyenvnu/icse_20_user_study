/** 
 * @return ???ID
 */
default Set<String> getRootOrgId(){
  return getOrgIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
}

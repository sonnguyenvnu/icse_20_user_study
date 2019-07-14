/** 
 * @return ???ID
 */
default Set<String> getRootDistrictId(){
  return getDistrictIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
}

/** 
 * @return ???ID
 */
default Set<String> getRootPositionId(){
  return getPositionIds().stream().map(TreeNode::getValue).collect(Collectors.toSet());
}

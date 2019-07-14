/** 
 * Check if switch statement contains default branch
 * @param node
 * @param data
 */
private void checkDefault(ASTSwitchStatement node,Object data){
  final String switchCheckXpath="SwitchLabel[@Default = 'true']";
  if (!node.hasDescendantMatchingXPath(switchCheckXpath)) {
    addViolationWithMessage(data,node,MESSAGE_KEY_PREFIX + ".nodefault");
  }
}

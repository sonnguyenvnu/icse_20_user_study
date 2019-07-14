/** 
 * Check the availability of break, return, throw, continue in case statement
 * @param node
 * @param data
 */
private void checkFallThrough(ASTSwitchStatement node,Object data){
  final String xpath="../SwitchStatement[(count(.//BreakStatement)" + " + count(BlockStatement//Statement/ReturnStatement)" + " + count(BlockStatement//Statement/ContinueStatement)" + " + count(BlockStatement//Statement/ThrowStatement)" + " + count(BlockStatement//Statement/IfStatement[@Else='true'" + " and Statement[2][ReturnStatement|ContinueStatement|ThrowStatement]]" + "/Statement[1][ReturnStatement|ContinueStatement|ThrowStatement])" + " + count(SwitchLabel[name(following-sibling::node()) = 'SwitchLabel'])" + " + count(SwitchLabel[count(following-sibling::node()) = 0])" + "  < count (SwitchLabel[@Default != 'true'])" + " + count(SwitchLabel[@Default = 'true']/following-sibling::BlockStatement//Statement/ReturnStatement)" + " + count(SwitchLabel[@Default = 'true']/following-sibling::BlockStatement//Statement/ContinueStatement)" + " + count(SwitchLabel[@Default = 'true']/following-sibling::BlockStatement//Statement/ThrowStatement)" + ")]";
  if (node.hasDescendantMatchingXPath(xpath)) {
    addViolationWithMessage(data,node,MESSAGE_KEY_PREFIX + ".notermination");
  }
}

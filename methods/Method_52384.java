/** 
 * Get the first parent. Keep track of the last node though. For If statements it's the only way we can differentiate between if's and else's For switches it's the only way we can differentiate between switches
 * @param node The node to check
 * @return The first parent block
 */
private Node getFirstParentBlock(Node node){
  Node parentNode=node.jjtGetParent();
  Node lastNode=node;
  while (parentNode != null && !BLOCK_PARENTS.contains(parentNode.getClass())) {
    lastNode=parentNode;
    parentNode=parentNode.jjtGetParent();
  }
  if (parentNode instanceof ASTIfStatement) {
    parentNode=lastNode;
  }
 else   if (parentNode instanceof ASTSwitchStatement) {
    parentNode=getSwitchParent(parentNode,lastNode);
  }
  return parentNode;
}

/** 
 * Checks whether the given childNode is part of an additive expression (String concatenation) limiting search to base Node.
 * @param childNode
 * @param baseNode
 * @return
 */
private boolean isStringConcat(Node childNode,Node baseNode){
  Node currentNode=childNode;
  while (!Objects.equals(currentNode,baseNode)) {
    currentNode=currentNode.jjtGetParent();
    if (currentNode instanceof ASTAdditiveExpression) {
      return true;
    }
  }
  return false;
}

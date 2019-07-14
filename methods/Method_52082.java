/** 
 * Check if the node will only be executed conditionally by checking, if the node is inside any kind of control flow statement or if any prior statement contains a  {@code continue} statement.<br> This doesn't check
 */
private boolean isConditionallyExecuted(Node node,ASTStatement loopBody){
  Node checkNode=node;
  while (checkNode.jjtGetParent() != null && !checkNode.jjtGetParent().equals(loopBody)) {
    final Node parent=checkNode.jjtGetParent();
    if (parent instanceof ASTIfStatement || parent instanceof ASTSwitchStatement || parent instanceof ASTWhileStatement || parent instanceof ASTDoStatement) {
      return !(checkNode instanceof ASTExpression);
    }
    if (parent instanceof ASTForStatement) {
      return !(checkNode instanceof ASTForInit || checkNode instanceof ASTExpression || checkNode instanceof ASTForUpdate);
    }
    checkNode=parent;
  }
  final ASTBlock block=loopBody.getFirstDescendantOfType(ASTBlock.class);
  if (block != null) {
    for (int i=0; i < block.jjtGetNumChildren(); i++) {
      final Node statement=block.jjtGetChild(i);
      if (statement.hasDescendantOfType(ASTContinueStatement.class)) {
        return true;
      }
      if (isParent(statement,node)) {
        return false;
      }
    }
  }
  return false;
}

/** 
 * Check if the given node is the first statement in the block.
 */
private boolean isFirstStatementInBlock(Node node,ASTStatement loopBody){
  final ASTBlockStatement statement=node.getFirstParentOfType(ASTBlockStatement.class);
  final ASTBlock block=loopBody.getFirstDescendantOfType(ASTBlock.class);
  if (statement == null || block == null) {
    return false;
  }
  return block.equals(statement.jjtGetParent()) && statement.jjtGetChildIndex() == 0;
}

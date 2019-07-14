/** 
 * Checks, whether there is a statement after the given if statement, and if so, whether this is just a return boolean statement.
 * @param node the if statement
 * @return
 */
private boolean isJustReturnsBooleanAfter(ASTIfStatement ifNode){
  Node blockStatement=ifNode.jjtGetParent().jjtGetParent();
  Node block=blockStatement.jjtGetParent();
  if (block.jjtGetNumChildren() != blockStatement.jjtGetChildIndex() + 1 + 1) {
    return false;
  }
  Node nextBlockStatement=block.jjtGetChild(blockStatement.jjtGetChildIndex() + 1);
  return terminatesInBooleanLiteral(nextBlockStatement);
}

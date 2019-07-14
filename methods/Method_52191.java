/** 
 * Returns all the block statements following the given local var declaration.
 */
private static List<ASTBlockStatement> statementsAfter(ASTLocalVariableDeclaration node){
  Node blockOrSwitch=node.jjtGetParent().jjtGetParent();
  int count=blockOrSwitch.jjtGetNumChildren();
  int start=node.jjtGetParent().jjtGetChildIndex() + 1;
  List<ASTBlockStatement> nextBlocks=new ArrayList<>(count - start);
  for (int i=start; i < count; i++) {
    Node maybeBlock=blockOrSwitch.jjtGetChild(i);
    if (maybeBlock instanceof ASTBlockStatement) {
      nextBlocks.add((ASTBlockStatement)maybeBlock);
    }
  }
  return nextBlocks;
}

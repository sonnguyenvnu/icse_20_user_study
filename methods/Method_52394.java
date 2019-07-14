/** 
 * This rule is concerned with IF and Switch blocks. Process the block into a local Map, from which we can later determine which is the longest block inside
 * @param blocks The map of blocks in the method being investigated
 * @param thisSize The size of the current block
 * @param block The block in question
 */
private void storeBlockStatistics(Map<Node,Map<Node,Integer>> blocks,int thisSize,Node block){
  Node statement=block.jjtGetParent();
  if (block.jjtGetParent() instanceof ASTIfStatement) {
    Node possibleStatement=statement.getFirstParentOfType(ASTIfStatement.class);
    while (possibleStatement instanceof ASTIfStatement) {
      statement=possibleStatement;
      possibleStatement=possibleStatement.getFirstParentOfType(ASTIfStatement.class);
    }
  }
  Map<Node,Integer> thisBranch=blocks.get(statement);
  if (thisBranch == null) {
    thisBranch=new HashMap<>();
    blocks.put(statement,thisBranch);
  }
  Integer x=thisBranch.get(block);
  if (x != null) {
    thisSize+=x;
  }
  thisBranch.put(statement,thisSize);
}

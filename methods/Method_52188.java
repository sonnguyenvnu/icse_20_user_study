/** 
 * Returns whether the block contains a return call or throws an exception. Exclude blocks that have these things as part of an inner class.
 */
private boolean hasExit(ASTBlockStatement block){
  return block.hasDescendantOfAnyType(ASTThrowStatement.class,ASTReturnStatement.class);
}

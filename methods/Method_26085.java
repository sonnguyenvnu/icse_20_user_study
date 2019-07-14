private boolean hasWhileTrue(TryTree tree,VisitorState state){
  return WHILE_TRUE_IN_BLOCK.matches(tree,state);
}

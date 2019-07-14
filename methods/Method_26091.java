private boolean hasContinue(TryTree tree,VisitorState state){
  return CONTINUE_IN_BLOCK.matches(tree,state);
}

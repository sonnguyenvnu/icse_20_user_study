private boolean hasThrowOrFail(TryTree tree,VisitorState state){
  return THROW_OR_FAIL_IN_BLOCK.matches(tree,state);
}

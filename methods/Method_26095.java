private boolean hasAssertInCatch(TryTree tree,VisitorState state){
  return anyCatchBlockMatches(tree,state,ASSERT_IN_BLOCK);
}

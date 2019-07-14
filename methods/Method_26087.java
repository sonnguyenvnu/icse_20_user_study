private boolean lastTryStatementIsAssert(TryTree tree,VisitorState state){
  return ASSERT_LAST_CALL_IN_TRY.matches(tree,state);
}

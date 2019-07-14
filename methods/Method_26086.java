private boolean hasBooleanAssertVariableInCatch(TryTree tree,VisitorState state){
  return anyCatchBlockMatches(tree,state,BOOLEAN_ASSERT_VAR_IN_BLOCK);
}

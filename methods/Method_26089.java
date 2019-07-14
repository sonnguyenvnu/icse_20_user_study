private boolean logsInCatch(VisitorState state,TryTree tree){
  return anyCatchBlockMatches(tree,state,LOG_IN_BLOCK);
}

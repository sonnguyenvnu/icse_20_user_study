@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!RELEASE.matches(tree,state)) {
    return NO_MATCH;
  }
  TryTree enclosingTry=findEnclosingNode(state.getPath(),TryTree.class);
  if (enclosingTry != null && tryCatchesException(enclosingTry,state.getSymtab().runtimeExceptionType,state)) {
    return NO_MATCH;
  }
  Symbol wakelockSymbol=getSymbol(getReceiver(tree));
  if (wakelockSymbol == null || !wakelockMayThrow(wakelockSymbol,state)) {
    return NO_MATCH;
  }
  Tree releaseStatement=state.getPath().getParentPath().getLeaf();
  return describeMatch(releaseStatement,getFix(releaseStatement,wakelockSymbol,state));
}

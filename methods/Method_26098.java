private boolean anyCatchBlockMatches(TryTree tree,VisitorState state,Matcher<Tree> matcher){
  for (  CatchTree catchTree : tree.getCatches()) {
    if (matcher.matches(catchTree.getBlock(),state)) {
      return true;
    }
  }
  return false;
}

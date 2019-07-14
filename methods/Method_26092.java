private boolean isInapplicableExceptionType(TryTree tree,VisitorState state){
  for (  CatchTree catchTree : tree.getCatches()) {
    if (INAPPLICABLE_EXCEPTION.matches(catchTree.getParameter(),state)) {
      return true;
    }
  }
  return false;
}

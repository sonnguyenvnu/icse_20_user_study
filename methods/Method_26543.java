@Override public Result visitContinue(ContinueTree node,BreakContext cxt){
  if (cxt.internalLabels.contains(node.getLabel()) || (node.getLabel() == null && cxt.loopDepth > 0)) {
    return NEVER_EXITS;
  }
 else {
    return MAY_BREAK_OR_RETURN;
  }
}

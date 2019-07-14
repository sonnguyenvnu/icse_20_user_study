@Override public Description matchSwitch(SwitchTree tree,VisitorState state){
  PeekingIterator<JCTree.JCCase> it=Iterators.peekingIterator(((JCTree.JCSwitch)tree).cases.iterator());
  while (it.hasNext()) {
    JCTree.JCCase caseTree=it.next();
    if (!it.hasNext()) {
      break;
    }
    JCTree.JCCase next=it.peek();
    if (caseTree.stats.isEmpty()) {
      continue;
    }
    boolean completes=Reachability.canCompleteNormally(getLast(caseTree.stats));
    String comments=state.getSourceCode().subSequence(caseEndPosition(state,caseTree),next.getStartPosition()).toString().trim();
    if (completes && !FALL_THROUGH_PATTERN.matcher(comments).find()) {
      state.reportMatch(buildDescription(next).setMessage("Execution may fall through from the previous case; add a `// fall through`" + " comment before this line if it was deliberate").build());
    }
 else     if (!completes && FALL_THROUGH_PATTERN.matcher(comments).find()) {
      state.reportMatch(buildDescription(next).setMessage("Switch case has 'fall through' comment, but execution cannot fall through" + " from the previous case").build());
    }
  }
  return NO_MATCH;
}

@Override public Description matchAssignment(AssignmentTree tree,VisitorState state){
  if (assignmentIncrementDecrementMatcher(tree.getVariable()).matches(tree,state)) {
    return describeMatch(tree);
  }
  return Description.NO_MATCH;
}

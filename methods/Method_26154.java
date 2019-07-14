@Override public Description matchCompoundAssignment(CompoundAssignmentTree tree,VisitorState state){
  if (!compoundAssignmentMatcher.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Compound assignment to a String or boxed primitive allocates a new object," + " which " + COMMON_MESSAGE_SUFFIX).build();
}

@Override public Description matchBinary(BinaryTree tree,VisitorState state){
  if (!stringConcatenationMatcher.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("String concatenation allocates a new String, which " + COMMON_MESSAGE_SUFFIX).build();
}

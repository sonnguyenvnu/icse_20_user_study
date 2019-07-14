@Override public Description matchNewArray(NewArrayTree tree,VisitorState state){
  if (!newArrayMatcher.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Allocating a new array with \"new\" or \"{ ... }\" " + COMMON_MESSAGE_SUFFIX).build();
}

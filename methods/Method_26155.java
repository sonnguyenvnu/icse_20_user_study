@Override public Description matchEnhancedForLoop(EnhancedForLoopTree tree,VisitorState state){
  if (!foreachMatcher.matches(tree,state)) {
    return NO_MATCH;
  }
  return buildDescription(tree).setMessage("Iterating over a Collection or iterating over a primitive array using a" + " non-primitive element type will trigger allocation, which " + COMMON_MESSAGE_SUFFIX).build();
}

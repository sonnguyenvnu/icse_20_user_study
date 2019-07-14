@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!PATTERN_CTOR_MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  return constructDescription(tree,tree.getArguments().get(0),state);
}

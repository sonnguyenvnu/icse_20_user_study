@Override public Description matchNewClass(NewClassTree newClassTree,VisitorState state){
  if (!MATCHER.matches(newClassTree,state)) {
    return Description.NO_MATCH;
  }
  StatementTree parent=(StatementTree)state.getPath().getParentPath().getLeaf();
  boolean isLastStatement=anyOf(new ChildOfBlockOrCase<>(ChildMultiMatcher.MatchType.LAST,Matchers.<StatementTree>isSame(parent)),parentNode(parentNode(kindIs(IF)))).matches(newClassTree,state);
  Fix fix;
  if (isLastStatement) {
    fix=SuggestedFix.prefixWith(newClassTree,"throw ");
  }
 else {
    fix=SuggestedFix.delete(parent);
  }
  return describeMatch(newClassTree,fix);
}

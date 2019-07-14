@Override public Description matchNewClass(NewClassTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  ExpressionTree millisArg=Iterables.getOnlyElement(tree.getArguments());
  SuggestedFix fix=SuggestedFix.replace(((JCTree)tree).getStartPosition(),((JCTree)millisArg).getStartPosition(),state.getSourceForNode(tree.getIdentifier()) + ".millis(");
  return describeMatch(tree,fix);
}

@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  SuggestedFix.Builder builder=SuggestedFix.builder();
  String replacement="new " + SuggestedFixes.qualifyType(state,builder,"org.joda.time.Instant") + "(";
  ExpressionTree millisArg=Iterables.getOnlyElement(tree.getArguments());
  builder.replace(((JCTree)tree).getStartPosition(),((JCTree)millisArg).getStartPosition(),replacement);
  return describeMatch(tree,builder.build());
}

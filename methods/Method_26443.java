@Override public Description matchMethodInvocation(MethodInvocationTree methodInvocationTree,VisitorState state){
  if (methodInvocationTree.getArguments().isEmpty()) {
    return Description.NO_MATCH;
  }
  Description.Builder description=buildDescription(methodInvocationTree);
  ExpressionTree toReplace=methodInvocationTree.getArguments().get(0);
  if (EQUALS_MATCHER.matches(methodInvocationTree,state)) {
    description.setMessage(generateSummary(ASTHelpers.getSymbol(methodInvocationTree).getSimpleName().toString(),"passes")).addFix(suggestEqualsTesterFix(methodInvocationTree,toReplace,state));
  }
 else   if (NOT_EQUALS_MATCHER.matches(methodInvocationTree,state)) {
    description.setMessage(generateSummary(ASTHelpers.getSymbol(methodInvocationTree).getSimpleName().toString(),"fails"));
  }
 else {
    return Description.NO_MATCH;
  }
  Fix fix=SelfEquals.fieldFix(toReplace,state);
  if (fix != null) {
    description.addFix(fix);
  }
  return description.build();
}

private static Fix suggestEqualsTesterFix(MethodInvocationTree methodInvocationTree,ExpressionTree toReplace,VisitorState state){
  String equalsTesterSuggest="new EqualsTester().addEqualityGroup(" + state.getSourceForNode(toReplace) + ").testEquals()";
  return SuggestedFix.builder().replace(methodInvocationTree,equalsTesterSuggest).addImport("com.google.common.testing.EqualsTester").build();
}

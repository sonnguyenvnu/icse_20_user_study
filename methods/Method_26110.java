private static ImmutableList<SuggestedFix> generateFixes(List<ReturnTree> returnTrees,TreePath methodTree,VisitorState state){
  SuggestedFix.Builder simpleFix=SuggestedFix.builder();
  SuggestedFix.Builder fixWithBuilders=SuggestedFix.builder();
  boolean anyBuilderFixes=false;
  Matcher<Tree> returnTypeMatcher=null;
  for (  Map.Entry<Matcher<Tree>,TypeDetails> entry : REFACTORING_DETAILS.entrySet()) {
    Tree returnType=((MethodTree)methodTree.getLeaf()).getReturnType();
    Matcher<Tree> matcher=entry.getKey();
    if (matcher.matches(returnType,state)) {
      if (!methodCanBeOverridden(getSymbol((MethodTree)methodTree.getLeaf()))) {
        SuggestedFix.Builder fixBuilder=SuggestedFix.builder();
        fixBuilder.replace(ASTHelpers.getErasedTypeTree(returnType),qualifyType(state,fixBuilder,entry.getValue().immutableType()));
        simpleFix.merge(fixBuilder);
        fixWithBuilders.merge(fixBuilder);
      }
      returnTypeMatcher=isSubtypeOf(entry.getValue().immutableType());
      break;
    }
  }
  if (returnTypeMatcher == null) {
    return ImmutableList.of();
  }
  for (  ReturnTree returnTree : returnTrees) {
    if (returnTypeMatcher.matches(returnTree.getExpression(),state)) {
      break;
    }
    for (    Map.Entry<Matcher<Tree>,TypeDetails> entry : REFACTORING_DETAILS.entrySet()) {
      Matcher<Tree> predicate=entry.getKey();
      TypeDetails typeDetails=entry.getValue();
      ExpressionTree expression=returnTree.getExpression();
      if (!predicate.matches(expression,state)) {
        continue;
      }
      if (expression instanceof IdentifierTree) {
        SuggestedFix simple=applySimpleFix(typeDetails.immutableType(),expression,state);
        ReturnTypeFixer returnTypeFixer=new ReturnTypeFixer(getSymbol(expression),typeDetails,state);
        returnTypeFixer.scan(methodTree,null);
        anyBuilderFixes|=!returnTypeFixer.failed;
        simpleFix.merge(simple);
        fixWithBuilders.merge(returnTypeFixer.failed ? simple : returnTypeFixer.fix.build());
        continue;
      }
      if (IMMUTABLE_FACTORY.matches(expression,state)) {
        SuggestedFix.Builder fix=SuggestedFix.builder();
        fix.replace(((MethodInvocationTree)expression).getMethodSelect(),qualifyType(state,fix,typeDetails.immutableType()) + ".of");
        simpleFix.merge(fix);
        fixWithBuilders.merge(fix);
        continue;
      }
      SuggestedFix simple=applySimpleFix(typeDetails.immutableType(),expression,state);
      simpleFix.merge(simple);
      fixWithBuilders.merge(simple);
    }
  }
  if (!anyBuilderFixes) {
    return ImmutableList.of(simpleFix.build());
  }
  return ImmutableList.of(simpleFix.build(),fixWithBuilders.setShortDescription("Fix using builders. Warning: this may change behaviour " + "if duplicate keys are added to ImmutableMap.Builder.").build());
}

@Override public Fix replace(BlockTemplateMatch match){
  checkNotNull(match);
  SuggestedFix.Builder fix=SuggestedFix.builder();
  Inliner inliner=match.createInliner();
  Context context=inliner.getContext();
  if (annotations().containsKey(UseImportPolicy.class)) {
    ImportPolicy.bind(context,annotations().getInstance(UseImportPolicy.class).value());
  }
 else {
    ImportPolicy.bind(context,ImportPolicy.IMPORT_TOP_LEVEL);
  }
  ImmutableList<JCStatement> targetStatements=match.getStatements();
  try {
    ImmutableList.Builder<JCStatement> inlinedStatementsBuilder=ImmutableList.builder();
    for (    UStatement statement : templateStatements()) {
      inlinedStatementsBuilder.addAll(statement.inlineStatements(inliner));
    }
    ImmutableList<JCStatement> inlinedStatements=inlinedStatementsBuilder.build();
    int nInlined=inlinedStatements.size();
    int nTargets=targetStatements.size();
    if (nInlined <= nTargets) {
      for (int i=0; i < nInlined; i++) {
        fix.replace(targetStatements.get(i),printStatement(context,inlinedStatements.get(i)));
      }
      for (int i=nInlined; i < nTargets; i++) {
        fix.delete(targetStatements.get(i));
      }
    }
 else {
      for (int i=0; i < nTargets - 1; i++) {
        fix.replace(targetStatements.get(i),printStatement(context,inlinedStatements.get(i)));
      }
      int last=nTargets - 1;
      ImmutableList<JCStatement> remainingInlined=inlinedStatements.subList(last,nInlined);
      fix.replace(targetStatements.get(last),CharMatcher.whitespace().trimTrailingFrom(printStatements(context,remainingInlined)));
    }
  }
 catch (  CouldNotResolveImportException e) {
    logger.log(SEVERE,"Failure to resolve import in replacement",e);
  }
  return addImports(inliner,fix);
}

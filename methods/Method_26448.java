@Override public Description matchTry(TryTree tree,VisitorState state){
  MatchResult matchResult=tryTreeMatches(tree,state);
  if (!matchResult.matched()) {
    return NO_MATCH;
  }
  Description.Builder builder=buildDescription(tree.getCatches().get(0).getParameter());
  if (matchResult.caughtType == JAVA_LANG_THROWABLE) {
    builder.addFix(fixByCatchingException(tree));
  }
  if (matchResult.caughtType == SOME_ASSERTION_FAILURE) {
    builder.addFix(fixByThrowingJavaLangError(matchResult.failStatement,state));
  }
  builder.addFix(fixWithReturnOrBoolean(tree,matchResult.failStatement,state));
  return builder.build();
}

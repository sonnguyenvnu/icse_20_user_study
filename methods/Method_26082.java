public static Fix addFailCall(TryTree tree,StatementTree lastTryStatement,VisitorState state){
  String failCall=String.format("\nfail(\"Expected %s\");",exceptionToString(tree,state));
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder().postfixWith(lastTryStatement,failCall);
  fixBuilder.removeStaticImport("junit.framework.Assert.fail");
  fixBuilder.removeStaticImport("junit.framework.TestCase.fail");
  fixBuilder.addStaticImport("org.junit.Assert.fail");
  return fixBuilder.build();
}

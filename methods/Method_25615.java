protected BaseFix buildBaseFix(VisitorState state,List<Tree> expectations,@Nullable StatementTree failure){
  String exceptionClassName="Throwable";
  String exceptionClassExpr="Throwable.class";
  List<String> newAsserts=new ArrayList<>();
  SuggestedFix.Builder fix=SuggestedFix.builder();
  for (  Tree expectation : expectations) {
    MethodInvocationTree invocation=(MethodInvocationTree)((ExpressionStatementTree)expectation).getExpression();
    MethodSymbol symbol=ASTHelpers.getSymbol(invocation);
    Symtab symtab=state.getSymtab();
    List<? extends ExpressionTree> args=invocation.getArguments();
switch (symbol.getSimpleName().toString()) {
case "expect":
      Type type=ASTHelpers.getType(getOnlyElement(invocation.getArguments()));
    if (isSubtype(type,symtab.classType,state)) {
      ExpressionTree arg=getOnlyElement(args);
      exceptionClassExpr=state.getSourceForNode(arg);
      ExpressionTree exceptionClassTree;
      try {
        exceptionClassTree=getReceiver(arg);
      }
 catch (      IllegalStateException e) {
        break;
      }
      exceptionClassName=state.getSourceForNode(exceptionClassTree);
    }
 else     if (isSubtype(type,state.getTypeFromString("org.hamcrest.Matcher"),state)) {
      Type matcherType=state.getTypes().asSuper(type,state.getSymbolFromString("org.hamcrest.Matcher"));
      if (!matcherType.getTypeArguments().isEmpty()) {
        Type matchType=getOnlyElement(matcherType.getTypeArguments());
        if (isSubtype(matchType,symtab.throwableType,state)) {
          exceptionClassName=SuggestedFixes.qualifyType(state,fix,matchType);
          exceptionClassExpr=exceptionClassName + ".class";
        }
      }
      fix.addStaticImport("org.hamcrest.MatcherAssert.assertThat");
      newAsserts.add(String.format("assertThat(thrown, %s);",state.getSourceForNode(getOnlyElement(args))));
    }
  break;
case "expectCause":
ExpressionTree matcher=getOnlyElement(invocation.getArguments());
if (IS_A.matches(matcher,state)) {
fix.addStaticImport("com.google.common.truth.Truth.assertThat");
newAsserts.add(String.format("assertThat(thrown).hasCauseThat().isInstanceOf(%s);",state.getSourceForNode(getOnlyElement(((MethodInvocationTree)matcher).getArguments()))));
}
 else {
fix.addStaticImport("org.hamcrest.MatcherAssert.assertThat");
newAsserts.add(String.format("assertThat(thrown.getCause(), %s);",state.getSourceForNode(getOnlyElement(args))));
}
break;
case "expectMessage":
if (isSubtype(getOnlyElement(symbol.getParameters()).asType(),symtab.stringType,state)) {
fix.addStaticImport("com.google.common.truth.Truth.assertThat");
newAsserts.add(String.format("assertThat(thrown).hasMessageThat().contains(%s);",state.getSourceForNode(getOnlyElement(args))));
}
 else {
fix.addStaticImport("org.hamcrest.MatcherAssert.assertThat");
newAsserts.add(String.format("assertThat(thrown.getMessage(), %s);",state.getSourceForNode(getOnlyElement(args))));
}
break;
default :
throw new AssertionError("unknown expect method: " + symbol.getSimpleName());
}
}
fix.replace(((JCTree)expectations.get(0)).getStartPosition(),state.getEndPosition(getLast(expectations)),"");
if (failure != null) {
fix.delete(failure);
}
return new BaseFix(fix.build(),exceptionClassName,exceptionClassExpr,newAsserts);
}

/** 
 * Creates a SuggestedFix that replaces the checkNotNull call with a checkArgument or checkState call.
 */
private Fix createCheckArgumentOrStateCall(MethodInvocationTree methodInvocationTree,VisitorState state,ExpressionTree arg1){
  SuggestedFix.Builder fix=SuggestedFix.builder();
  String replacementMethod="checkState";
  if (hasMethodParameter(state.getPath(),arg1)) {
    replacementMethod="checkArgument";
  }
  StringBuilder replacement=new StringBuilder();
  if (methodInvocationTree.getMethodSelect().getKind() == Kind.IDENTIFIER) {
    fix.addStaticImport("com.google.common.base.Preconditions." + replacementMethod);
  }
 else {
    replacement.append("Preconditions.");
  }
  replacement.append(replacementMethod).append('(');
  Joiner.on(", ").appendTo(replacement,methodInvocationTree.getArguments());
  replacement.append(")");
  fix.replace(methodInvocationTree,replacement.toString());
  return fix.build();
}

private static ImmutableList<Fix> literalReplacement(MethodInvocationTree methodInvocationTree,VisitorState state,ExpressionTree lhs){
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (parent instanceof ExpressionStatementTree) {
    Fix fix;
    if (instanceMethod().anyClass().named("removeAll").matches(methodInvocationTree,state)) {
      fix=SuggestedFix.replace(methodInvocationTree,state.getSourceForNode(lhs) + ".clear()");
    }
 else {
      fix=SuggestedFix.delete(parent);
    }
    return ImmutableList.of(fix);
  }
  return ImmutableList.of();
}

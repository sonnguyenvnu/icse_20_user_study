@Nullable private Description checkExpression(ExpressionTree tree,VisitorState state,Function<String,String> describer){
  Nullness nullness=TrustingNullnessAnalysis.instance(state.context).getNullness(new TreePath(state.getPath(),tree),state.context);
  if (nullness == null) {
    return null;
  }
switch (nullness) {
case NONNULL:
case BOTTOM:
    return null;
case NULL:
  return buildDescription(tree).setMessage(describer.apply("definitely")).build();
case NULLABLE:
return buildDescription(tree).setMessage(describer.apply("possibly")).build();
}
throw new AssertionError("Unhandled: " + nullness);
}

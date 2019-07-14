@Nullable private List<UExpression> templateExpressions(@Nullable Iterable<? extends ExpressionTree> expressions){
  if (expressions == null) {
    return null;
  }
  ImmutableList.Builder<UExpression> builder=ImmutableList.builder();
  for (  ExpressionTree expression : expressions) {
    builder.add(template(expression));
  }
  return builder.build();
}

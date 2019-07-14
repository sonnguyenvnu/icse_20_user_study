@Nullable private List<UExpression> templateTypeExpressions(@Nullable Iterable<? extends Tree> types){
  if (types == null) {
    return null;
  }
  ImmutableList.Builder<UExpression> builder=ImmutableList.builder();
  for (  Tree type : types) {
    builder.add(templateType(type));
  }
  return builder.build();
}

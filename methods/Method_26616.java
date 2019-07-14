public static UAnyOf create(Iterable<? extends UExpression> expressions){
  return new AutoValue_UAnyOf(ImmutableList.copyOf(expressions));
}

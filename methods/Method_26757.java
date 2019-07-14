static UUnionType create(Iterable<? extends UExpression> typeAlternatives){
  return new AutoValue_UUnionType(ImmutableList.copyOf(typeAlternatives));
}

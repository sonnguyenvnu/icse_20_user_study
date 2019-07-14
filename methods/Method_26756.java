@VisibleForTesting static UUnionType create(UExpression... typeAlternatives){
  return create(ImmutableList.copyOf(typeAlternatives));
}

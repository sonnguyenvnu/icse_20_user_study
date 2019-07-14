private static Optional<Nullness> extractExplicitNullness(@Nullable Type type,@Nullable Symbol symbol){
  if (symbol != null) {
    Optional<Nullness> result=NullnessAnnotations.fromAnnotationsOn(symbol);
    if (result.isPresent()) {
      return result;
    }
  }
  return NullnessAnnotations.fromAnnotationsOn(type);
}

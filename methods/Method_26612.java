public static UAnnotatedType create(Iterable<UAnnotation> annotations,UExpression type){
  return new AutoValue_UAnnotatedType(ImmutableList.copyOf(annotations),type);
}

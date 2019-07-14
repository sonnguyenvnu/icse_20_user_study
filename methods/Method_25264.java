public static boolean hasNullableAnnotation(Symbol symbol){
  return NullnessAnnotations.fromAnnotationsOn(symbol).orElse(null) == Nullness.NULLABLE;
}

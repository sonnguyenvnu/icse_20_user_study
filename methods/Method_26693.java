public static UNewArray create(UExpression type,List<? extends UExpression> dimensions,List<? extends UExpression> initializers){
  return new AutoValue_UNewArray(type,dimensions != null ? ImmutableList.copyOf(dimensions) : null,initializers != null ? ImmutableList.copyOf(initializers) : null);
}

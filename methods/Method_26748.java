public static UTypeApply create(UExpression type,List<UExpression> typeArguments){
  return new AutoValue_UTypeApply(type,ImmutableList.copyOf(typeArguments));
}

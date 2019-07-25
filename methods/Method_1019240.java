public static TargetInfoRequest create(Multimap<String,String> userParameters){
  return new AutoValue_TargetInfoRequest(ImmutableSetMultimap.copyOf(userParameters));
}

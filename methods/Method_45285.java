public static RestIdMatcher match(final String uri){
  return new BaseRestIdMatcher(checkNotNullOrEmpty(uri,"Match target should not be null or empty"));
}

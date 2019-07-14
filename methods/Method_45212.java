public static RestSettingBuilder head(final RestIdMatcher idMatcher){
  return single(HttpMethod.HEAD,checkNotNull(idMatcher,"ID Matcher should not be null"));
}

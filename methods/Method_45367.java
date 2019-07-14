private ImmutableList<RequestMatcher> createRequestMatchers(final RequestSetting request){
  return from(getFields(RequestSetting.class)).filter(isValidField(request)).transform(fieldToRequestMatcher(request)).toList();
}

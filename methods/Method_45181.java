@Override public final HttpResponseSetting put(final RequestMatcher matcher){
  return requestByHttpMethod(HttpMethod.PUT,checkNotNull(matcher,"Matcher should not be null"));
}

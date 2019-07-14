@Override protected BaseSetting<HttpResponseSetting> createSetting(final RequestMatcher matcher){
  return new HttpSetting(matcher);
}

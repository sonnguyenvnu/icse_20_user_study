@Override protected final HttpSetting newSetting(final RequestMatcher matcher){
  return new HttpSetting(matcher);
}

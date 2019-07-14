@Override protected final HttpResponseSetting onRequestAttached(final RequestMatcher matcher){
  HttpSetting baseSetting=new HttpSetting(matcher);
  addSetting(baseSetting);
  return baseSetting;
}

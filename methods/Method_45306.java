@Override protected final BaseSetting<SocketResponseSetting> createSetting(final RequestMatcher matcher){
  return new SocketSetting(matcher);
}

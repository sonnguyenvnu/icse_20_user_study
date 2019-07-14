@Override protected Setting<SocketResponseSetting> newSetting(final RequestMatcher matcher){
  return new SocketSetting(matcher);
}

@Override protected SocketResponseSetting onRequestAttached(final RequestMatcher matcher){
  SocketSetting baseSetting=new SocketSetting(matcher);
  addSetting(baseSetting);
  return baseSetting;
}

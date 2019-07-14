@Override public boolean needToLoad(FilterInvoker invoker){
  AbstractInterfaceConfig config=invoker.config;
  String invokerId=config.getId();
  if (!formatComplete) {
synchronized (formatLock) {
      if (!formatComplete) {
        formatId(idRule);
        formatComplete=true;
      }
    }
  }
  return isMatch(invokerId);
}

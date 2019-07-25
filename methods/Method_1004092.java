@Override public void startup(final RuntimeData data) throws Exception {
  super.startup(data);
  handlers=getHandlersReference();
  handlers.put(protocol,handler);
}

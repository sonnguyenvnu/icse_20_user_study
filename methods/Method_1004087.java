@Override public void startup(final RuntimeData data) throws Exception {
  super.startup(data);
  this.logger.addHandler(handler);
}

@Override public void startup(final RuntimeData data) throws Exception {
  super.startup(data);
  System.getProperties().put(key,data);
}

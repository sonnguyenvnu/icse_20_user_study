@Override public void removeProvider(ProviderGroup group){
  if (cluster != null) {
    boolean originalState=cluster.isAvailable();
    cluster.removeProvider(group);
    cluster.checkStateChange(originalState);
  }
}

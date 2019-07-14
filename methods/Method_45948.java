@Override public void updateProviders(ProviderGroup group){
  if (cluster != null) {
    boolean originalState=cluster.isAvailable();
    cluster.updateProviders(group);
    cluster.checkStateChange(originalState);
  }
}

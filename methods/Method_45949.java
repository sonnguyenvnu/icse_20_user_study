@Override public void updateAllProviders(List<ProviderGroup> groups){
  if (cluster != null) {
    boolean originalState=cluster.isAvailable();
    cluster.updateAllProviders(groups);
    cluster.checkStateChange(originalState);
  }
}

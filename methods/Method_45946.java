@Override public void addProvider(ProviderGroup group){
  if (cluster != null) {
    boolean originalState=cluster.isAvailable();
    cluster.addProvider(group);
    cluster.checkStateChange(originalState);
  }
}

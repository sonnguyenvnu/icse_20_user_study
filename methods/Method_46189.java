@Override public void batchUnRegister(List<ProviderConfig> configs){
  for (  ProviderConfig config : configs) {
    unRegister(config);
  }
}

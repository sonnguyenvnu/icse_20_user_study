@Override public void batchUnRegister(List<ProviderConfig> configs){
  configs.forEach(this::unRegister);
}

public ConfigurationBuilder setMediaProviderParameters(Properties props){
  checkNotBuilt();
  configurationBean.setMediaProviderParameters(props);
  return this;
}

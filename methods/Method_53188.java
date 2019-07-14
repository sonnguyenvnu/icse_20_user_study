public ConfigurationBuilder setUserStreamWithFollowingsEnabled(boolean enabled){
  checkNotBuilt();
  configurationBean.setUserStreamWithFollowingsEnabled(enabled);
  return this;
}

public Configuration build(){
  checkNotBuilt();
  configurationBean.cacheInstance();
  try {
    return configurationBean;
  }
  finally {
    configurationBean=null;
  }
}

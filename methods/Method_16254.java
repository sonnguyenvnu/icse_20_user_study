@Override @Cacheable(key="'id:'+#configId+'.'+#key+'-as-string'") public String getString(String configId,String key,String defaultValue){
  return getConfigContent(configId,key).map(conf -> conf.getValue(defaultValue)).map(String::valueOf).orElse(defaultValue);
}

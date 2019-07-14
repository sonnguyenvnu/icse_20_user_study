@Override @Cacheable(key="'id:'+#configId+'.'+#key+'-as-number'") public Number getNumber(String configId,String key,Number defaultValue){
  return getConfigContent(configId,key).map(conf -> conf.getNumber(defaultValue)).orElse(defaultValue);
}

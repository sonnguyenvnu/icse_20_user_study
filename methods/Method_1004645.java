private void process(String prefix,Map cacheBuilders,boolean local){
  ConfigTree resolver=new ConfigTree(environment,prefix);
  Map<String,Object> m=resolver.getProperties();
  Set<String> cacheAreaNames=resolver.directChildrenKeys();
  for (  String cacheArea : cacheAreaNames) {
    final Object configType=m.get(cacheArea + ".type");
    boolean match=Arrays.stream(typeNames).anyMatch((tn) -> tn.equals(configType));
    if (!match) {
      continue;
    }
    ConfigTree ct=resolver.subTree(cacheArea + ".");
    logger.info("init cache area {} , type= {}",cacheArea,typeNames[0]);
    CacheBuilder c=initCache(ct,local ? "local." + cacheArea : "remote." + cacheArea);
    cacheBuilders.put(cacheArea,c);
  }
}

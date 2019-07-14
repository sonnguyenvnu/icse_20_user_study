public Map<String,Object> getSubset(ConfigNamespace umbrella,String... umbrellaElements){
  Map<String,Object> result=Maps.newHashMap();
  for (  ReadConfiguration config : new ReadConfiguration[]{global,local}) {
    result.putAll(super.getSubset(config,umbrella,umbrellaElements));
  }
  return result;
}

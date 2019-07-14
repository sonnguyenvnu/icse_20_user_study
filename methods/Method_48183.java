protected Map<String,Object> getSubset(ReadConfiguration config,ConfigNamespace umbrella,String... umbrellaElements){
  verifyElement(umbrella);
  String prefix=umbrella.isRoot() ? "" : ConfigElement.getPath(umbrella,umbrellaElements);
  Map<String,Object> result=Maps.newHashMap();
  for (  String key : config.getKeys(prefix)) {
    Preconditions.checkArgument(key.startsWith(prefix));
    int startIndex=umbrella.isRoot() ? prefix.length() : prefix.length() + 1;
    String sub=key.substring(startIndex).trim();
    if (!sub.isEmpty()) {
      result.put(sub,config.get(key,Object.class));
    }
  }
  return result;
}

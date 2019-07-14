protected Set<String> getContainedNamespaces(ReadConfiguration config,ConfigNamespace umbrella,String... umbrellaElements){
  verifyElement(umbrella);
  Preconditions.checkArgument(umbrella.isUmbrella());
  String prefix=ConfigElement.getPath(umbrella,umbrellaElements);
  Set<String> result=Sets.newHashSet();
  for (  String key : config.getKeys(prefix)) {
    Preconditions.checkArgument(key.startsWith(prefix));
    String sub=key.substring(prefix.length() + 1).trim();
    if (!sub.isEmpty()) {
      String ns=ConfigElement.getComponents(sub)[0];
      Preconditions.checkArgument(StringUtils.isNotBlank(ns),"Invalid sub-namespace for key: %s",key);
      result.add(ns);
    }
  }
  return result;
}

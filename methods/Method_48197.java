public Map<ConfigElement.PathIdentifier,Object> getAll(){
  Map<ConfigElement.PathIdentifier,Object> result=Maps.newHashMap();
  for (  String key : config.getKeys("")) {
    Preconditions.checkArgument(StringUtils.isNotBlank(key));
    try {
      final ConfigElement.PathIdentifier pid=ConfigElement.parse(getRootNamespace(),key);
      Preconditions.checkArgument(pid.element.isOption() && !pid.lastIsUmbrella);
      result.put(pid,get((ConfigOption)pid.element,pid.umbrellaElements));
    }
 catch (    IllegalArgumentException e) {
      log.debug("Ignored configuration entry for {} since it does not map to an option",key,e);
    }
  }
  return result;
}

/** 
 * ??????.
 * @param fromCache ????????
 * @return ????
 */
public LiteJobConfiguration load(final boolean fromCache){
  String result;
  if (fromCache) {
    result=jobNodeStorage.getJobNodeData(ConfigurationNode.ROOT);
    if (null == result) {
      result=jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT);
    }
  }
 else {
    result=jobNodeStorage.getJobNodeDataDirectly(ConfigurationNode.ROOT);
  }
  return LiteJobConfigurationGsonFactory.fromJson(result);
}

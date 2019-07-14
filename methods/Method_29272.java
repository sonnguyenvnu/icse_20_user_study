@Override public List<String> handleClusterConfig(int port){
  List<InstanceConfig> instanceConfigList=getByType(ConstUtils.CACHE_TYPE_REDIS_CLUSTER);
  if (CollectionUtils.isEmpty(instanceConfigList)) {
    return Collections.emptyList();
  }
  List<String> configs=new ArrayList<String>();
  for (  InstanceConfig instanceConfig : instanceConfigList) {
    if (!instanceConfig.isEffective()) {
      continue;
    }
    String configKey=instanceConfig.getConfigKey();
    String configValue=instanceConfig.getConfigValue();
    if (StringUtils.isBlank(configValue)) {
      configValue=SPECIAL_EMPTY_STR;
    }
    if (RedisClusterConfigEnum.CLUSTER_CONFIG_FILE.getKey().equals(configKey)) {
      configValue=String.format(configValue,port);
    }
    configs.add(combineConfigKeyValue(configKey,configValue));
  }
  return configs;
}

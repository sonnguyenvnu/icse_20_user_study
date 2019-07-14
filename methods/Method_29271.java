@Override public List<String> handleSentinelConfig(String masterName,String host,int port,int sentinelPort){
  List<InstanceConfig> instanceConfigList=instanceConfigDao.getByType(ConstUtils.CACHE_REDIS_SENTINEL);
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
    if (RedisSentinelConfigEnum.PORT.getKey().equals(configKey)) {
      configValue=String.format(configValue,sentinelPort);
    }
 else     if (RedisSentinelConfigEnum.MONITOR.getKey().equals(configKey)) {
      configValue=String.format(configValue,masterName,host,port);
    }
 else     if (RedisSentinelConfigEnum.DOWN_AFTER_MILLISECONDS.getKey().equals(configKey) || RedisSentinelConfigEnum.FAILOVER_TIMEOUT.getKey().equals(configKey) || RedisSentinelConfigEnum.PARALLEL_SYNCS.getKey().equals(configKey)) {
      configValue=String.format(configValue,masterName);
    }
 else     if (RedisConfigEnum.DIR.getKey().equals(configKey)) {
      configValue=MachineProtocol.DATA_DIR;
    }
    configs.add(combineConfigKeyValue(configKey,configValue));
  }
  return configs;
}

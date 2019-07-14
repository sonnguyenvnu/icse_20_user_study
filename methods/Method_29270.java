@Override public List<String> handleCommonConfig(int port,int maxMemory){
  List<InstanceConfig> instanceConfigList=getByType(ConstUtils.CACHE_REDIS_STANDALONE);
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
    if (RedisConfigEnum.MAXMEMORY.getKey().equals(configKey)) {
      configValue=String.format(configValue,maxMemory);
    }
 else     if (RedisConfigEnum.DBFILENAME.getKey().equals(configKey) || RedisConfigEnum.APPENDFILENAME.getKey().equals(configKey) || RedisConfigEnum.PORT.getKey().equals(configKey)) {
      configValue=String.format(configValue,port);
    }
 else     if (RedisConfigEnum.DIR.getKey().equals(configKey)) {
      configValue=MachineProtocol.DATA_DIR;
    }
 else     if (RedisConfigEnum.AUTO_AOF_REWRITE_PERCENTAGE.getKey().equals(configKey)) {
      int percent=69 + new Random().nextInt(30);
      configValue=String.format(configValue,percent);
    }
    configs.add(combineConfigKeyValue(configKey,configValue));
  }
  return configs;
}

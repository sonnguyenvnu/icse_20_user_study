private List<String> handleSentinelConfig(String masterName,String host,int port,int sentinelPort){
  List<String> configs=null;
  try {
    configs=redisConfigTemplateService.handleSentinelConfig(masterName,host,port,sentinelPort);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  if (CollectionUtils.isEmpty(configs)) {
    configs=redisConfigTemplateService.handleSentinelDefaultConfig(masterName,host,port,sentinelPort);
  }
  return configs;
}

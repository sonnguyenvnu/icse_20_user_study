private List<String> handleClusterConfig(int port){
  List<String> configs=null;
  try {
    configs=redisConfigTemplateService.handleClusterConfig(port);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  if (CollectionUtils.isEmpty(configs)) {
    configs=redisConfigTemplateService.handleClusterDefaultConfig(port);
  }
  return configs;
}

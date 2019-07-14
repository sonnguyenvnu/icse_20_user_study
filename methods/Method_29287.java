/** 
 * ??redis ????
 * @param port
 * @param maxMemory
 * @return
 */
public List<String> handleCommonConfig(int port,int maxMemory){
  List<String> configs=null;
  try {
    configs=redisConfigTemplateService.handleCommonConfig(port,maxMemory);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  if (CollectionUtils.isEmpty(configs)) {
    configs=redisConfigTemplateService.handleCommonDefaultConfig(port,maxMemory);
  }
  return configs;
}

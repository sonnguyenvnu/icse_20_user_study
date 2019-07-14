/** 
 * ??redis??
 * @param sourceHost
 * @param sourcePort
 * @param targetHost
 * @param targetPort
 * @return
 */
private boolean copyCommonConfig(long appId,String sourceHost,int sourcePort,String targetHost,int targetPort){
  String[] compareConfigs=new String[]{"maxmemory-policy","maxmemory","cluster-node-timeout","cluster-require-full-coverage","repl-backlog-size","appendonly","hash-max-ziplist-entries","hash-max-ziplist-value","list-max-ziplist-entries","list-max-ziplist-value","set-max-intset-entries","zset-max-ziplist-entries","zset-max-ziplist-value","timeout","tcp-keepalive"};
  try {
    for (    String config : compareConfigs) {
      String sourceValue=getConfigValue(appId,sourceHost,sourcePort,config);
      if (StringUtils.isBlank(sourceValue)) {
        continue;
      }
      String targetValue=getConfigValue(appId,targetHost,targetPort,config);
      if (StringUtils.isNotBlank(targetHost)) {
        if (!targetValue.equals(sourceValue)) {
          this.modifyInstanceConfig(appId,targetHost,targetPort,config,sourceValue);
        }
      }
    }
    return true;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
}

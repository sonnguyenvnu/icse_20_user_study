/** 
 * ????
 * @param sourceRedisMigrateEnum
 * @param sourceServers
 * @param targetRedisMigrateEnum
 * @param targetServers
 * @return
 */
public String generateConfig(int listenPort,AppDataMigrateEnum sourceRedisMigrateEnum,String sourceServers,AppDataMigrateEnum targetRedisMigrateEnum,String targetServers,String redisSourcePass,String redisTargetPass){
  StringBuffer config=new StringBuffer();
  config.append("[source]" + ConstUtils.NEXT_LINE);
  config.append("type: " + sourceRedisMigrateEnum.getType() + ConstUtils.NEXT_LINE);
  config.append("servers:" + ConstUtils.NEXT_LINE);
  List<String> sourceServerList=Arrays.asList(sourceServers.split(ConstUtils.NEXT_LINE));
  for (  String server : sourceServerList) {
    config.append(" - " + server + ConstUtils.NEXT_LINE);
  }
  if (StringUtils.isNotBlank(redisSourcePass)) {
    config.append("redis_auth: " + redisSourcePass + ConstUtils.NEXT_LINE);
  }
  config.append(ConstUtils.NEXT_LINE);
  config.append("[target]" + ConstUtils.NEXT_LINE);
  config.append("type: " + targetRedisMigrateEnum.getType() + ConstUtils.NEXT_LINE);
  if (!AppDataMigrateEnum.isFileType(targetRedisMigrateEnum)) {
    config.append("servers:" + ConstUtils.NEXT_LINE);
    List<String> targetServerList=Arrays.asList(targetServers.split(ConstUtils.NEXT_LINE));
    for (    String server : targetServerList) {
      config.append(" - " + server + ConstUtils.NEXT_LINE);
    }
    if (StringUtils.isNotBlank(redisTargetPass)) {
      config.append("redis_auth: " + redisTargetPass + ConstUtils.NEXT_LINE);
    }
    config.append(ConstUtils.NEXT_LINE);
  }
  config.append("[common]" + ConstUtils.NEXT_LINE);
  config.append("listen: 0.0.0.0:" + listenPort + ConstUtils.NEXT_LINE);
  config.append("dir: " + ConstUtils.getRedisMigrateToolDir());
  return config.toString();
}

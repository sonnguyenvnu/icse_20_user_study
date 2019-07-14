/** 
 * ????
 * @param migrateMachineIp
 * @param redisMigrateEnum
 * @param servers
 * @param redisSourcePass ???
 * @return
 */
private AppDataMigrateResult checkMigrateConfig(String migrateMachineIp,AppDataMigrateEnum redisMigrateEnum,String servers,String redisPassword,boolean isSource){
  if (isSource || !AppDataMigrateEnum.isFileType(redisMigrateEnum)) {
    if (StringUtils.isBlank(servers)) {
      return AppDataMigrateResult.fail("?????????!");
    }
  }
  List<String> serverList=Arrays.asList(servers.split(ConstUtils.NEXT_LINE));
  if (CollectionUtils.isEmpty(serverList)) {
    return AppDataMigrateResult.fail("??????????!");
  }
  for (  String server : serverList) {
    if (AppDataMigrateEnum.isFileType(redisMigrateEnum)) {
      if (!isSource) {
        continue;
      }
      String filePath=server;
      String cmd="head " + filePath;
      try {
        String headResult=SSHUtil.execute(migrateMachineIp,cmd);
        if (StringUtils.isBlank(headResult)) {
          return AppDataMigrateResult.fail(migrateMachineIp + "??rdb:" + filePath + "???????!");
        }
      }
 catch (      Exception e) {
        logger.error(e.getMessage());
        return AppDataMigrateResult.fail(migrateMachineIp + "??rdb:" + filePath + "????!");
      }
    }
 else {
      String[] instanceItems=server.split(":");
      if (instanceItems.length != 2) {
        return AppDataMigrateResult.fail("????" + server + "????????ip:port??");
      }
      String ip=instanceItems[0];
      String portStr=instanceItems[1];
      boolean portIsDigit=NumberUtils.isDigits(portStr);
      if (!portIsDigit) {
        return AppDataMigrateResult.fail(server + "??port????");
      }
      int port=NumberUtils.toInt(portStr);
      boolean isRun=redisCenter.isRun(ip,port,redisPassword);
      if (!isRun) {
        return AppDataMigrateResult.fail(server + "???????????!");
      }
    }
  }
  return AppDataMigrateResult.success();
}

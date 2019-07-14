@Override public Map<RedisMigrateToolConstant,Map<String,Object>> showMiragteToolProcess(long id){
  AppDataMigrateStatus appDataMigrateStatus=appDataMigrateStatusDao.get(id);
  if (appDataMigrateStatus == null) {
    return Collections.emptyMap();
  }
  String info="";
  String host=appDataMigrateStatus.getMigrateMachineIp();
  int port=appDataMigrateStatus.getMigrateMachinePort();
  Jedis jedis=null;
  try {
    jedis=new Jedis(host,port,5000);
    info=jedis.info();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    if (jedis != null) {
      jedis.close();
    }
  }
  if (StringUtils.isBlank(info)) {
    return Collections.emptyMap();
  }
  return processRedisMigrateToolStats(info);
}

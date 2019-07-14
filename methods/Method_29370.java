@Override public String showDataMigrateConf(long id){
  AppDataMigrateStatus appDataMigrateStatus=appDataMigrateStatusDao.get(id);
  if (appDataMigrateStatus == null) {
    return "";
  }
  String configPath=appDataMigrateStatus.getConfigPath();
  String host=appDataMigrateStatus.getMigrateMachineIp();
  String command="cat " + configPath;
  try {
    return SSHUtil.execute(host,command);
  }
 catch (  SSHException e) {
    logger.error(e.getMessage(),e);
    return "";
  }
}

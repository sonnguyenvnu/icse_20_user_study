@Override public String showDataMigrateLog(long id,int pageSize){
  AppDataMigrateStatus appDataMigrateStatus=appDataMigrateStatusDao.get(id);
  if (appDataMigrateStatus == null) {
    return "";
  }
  String logPath=appDataMigrateStatus.getLogPath();
  String host=appDataMigrateStatus.getMigrateMachineIp();
  StringBuilder command=new StringBuilder();
  command.append("/usr/bin/tail -n").append(pageSize).append(" ").append(logPath);
  try {
    return SSHUtil.execute(host,command.toString());
  }
 catch (  SSHException e) {
    logger.error(e.getMessage(),e);
    return "";
  }
}

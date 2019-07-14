@Override public AppDataMigrateResult stopMigrate(long id){
  AppDataMigrateStatus appDataMigrateStatus=appDataMigrateStatusDao.get(id);
  if (appDataMigrateStatus == null) {
    return AppDataMigrateResult.fail("id=" + id + "???????!");
  }
  String migrateMachineIp=appDataMigrateStatus.getMigrateMachineIp();
  String migrateMachineHostPort=migrateMachineIp + ":" + appDataMigrateStatus.getMigrateMachinePort();
  Map<RedisMigrateToolConstant,Map<String,Object>> redisMigrateToolStatMap=showMiragteToolProcess(id);
  if (MapUtils.isEmpty(redisMigrateToolStatMap)) {
    return AppDataMigrateResult.fail("??" + migrateMachineHostPort + "???????????????????????????????!");
  }
  Map<String,Object> serverMap=redisMigrateToolStatMap.get(RedisMigrateToolConstant.Server);
  int pid=MapUtils.getInteger(serverMap,"process_id",-1);
  if (pid <= 0) {
    return AppDataMigrateResult.fail("??" + migrateMachineHostPort + "????" + pid + "??");
  }
  Boolean exist=checkPidWhetherIsRmt(migrateMachineIp,pid);
  if (exist == null) {
    return AppDataMigrateResult.fail("?????????,???????!");
  }
 else   if (exist.equals(false)) {
    return AppDataMigrateResult.fail(migrateMachineIp + "???" + pid + "???,???!");
  }
  try {
    String cmd="kill " + pid;
    SSHUtil.execute(migrateMachineIp,cmd);
    exist=checkPidWhetherIsRmt(migrateMachineIp,pid);
    if (exist == null) {
      return AppDataMigrateResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
    }
 else     if (exist.equals(false)) {
      appDataMigrateStatusDao.updateStatus(id,AppDataMigrateStatusEnum.END.getStatus());
      return AppDataMigrateResult.success("???????id=" + id + "?????");
    }
 else {
      return AppDataMigrateResult.fail(migrateMachineIp + "???" + pid + "????,??kill?,???!");
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage());
    return AppDataMigrateResult.fail(ErrorMessageEnum.INNER_ERROR_MSG.getMessage());
  }
}

@Override public ImportAppResult check(AppDesc appDesc,String appInstanceInfo){
  if (appDesc == null) {
    return ImportAppResult.fail("??????");
  }
  String appName=appDesc.getName();
  AppDesc existAppDesc=appService.getAppByName(appName);
  if (existAppDesc != null) {
    return ImportAppResult.fail(appName + ", ?????");
  }
  if (StringUtils.isBlank(appInstanceInfo)) {
    return ImportAppResult.fail("??????");
  }
  String[] appInstanceDetails=appInstanceInfo.split("\n");
  for (  String appInstance : appInstanceDetails) {
    if (StringUtils.isBlank(appInstance)) {
      return ImportAppResult.fail("?????????");
    }
    String[] instanceItems=appInstance.split(":");
    if (instanceItems.length != 3) {
      return ImportAppResult.fail("??????" + appInstance + "????????2???");
    }
    String ip=instanceItems[0];
    try {
      MachineInfo machineInfo=machineCenter.getMachineInfoByIp(ip);
      if (machineInfo == null) {
        return ImportAppResult.fail(appInstance + "??ip???");
      }
 else       if (machineInfo.isOffline()) {
        return ImportAppResult.fail(appInstance + "??ip?????");
      }
    }
 catch (    Exception e) {
      return ImportAppResult.fail(appInstance + "??ip???");
    }
    String portStr=instanceItems[1];
    boolean portIsDigit=NumberUtils.isDigits(portStr);
    if (!portIsDigit) {
      return ImportAppResult.fail(appInstance + "??port????");
    }
    int port=NumberUtils.toInt(portStr);
    int count=instanceDao.getCountByIpAndPort(ip,port);
    if (count > 0) {
      return ImportAppResult.fail(appInstance + "?ip:port???instance_info??");
    }
    InstanceStats instanceStats=instanceStatsDao.getInstanceStatsByHost(ip,port);
    if (instanceStats != null) {
      return ImportAppResult.fail(appInstance + "?ip:port???instance_statistics??");
    }
    String memoryOrMasterName=instanceItems[2];
    int memoryOrMasterNameInt=NumberUtils.toInt(memoryOrMasterName);
    boolean isRun;
    if (memoryOrMasterNameInt > 0) {
      isRun=redisCenter.isRun(ip,port,appDesc.getPassword());
    }
 else {
      isRun=redisCenter.isRun(ip,port);
    }
    if (!isRun) {
      return ImportAppResult.fail(appInstance + "?????????");
    }
    boolean isSentinelNode=memoryOrMasterNameInt <= 0;
    if (isSentinelNode) {
      if (StringUtils.isEmpty(memoryOrMasterName)) {
        return ImportAppResult.fail(appInstance + "??sentinel??master??");
      }
      String masterName=getSentinelMasterName(ip,port);
      if (StringUtils.isEmpty(masterName) || !memoryOrMasterName.equals(masterName)) {
        return ImportAppResult.fail(ip + ":" + port + ", masterName:" + masterName + "???" + memoryOrMasterName + "???");
      }
    }
 else {
      boolean maxMemoryIsDigit=NumberUtils.isDigits(memoryOrMasterName);
      if (!maxMemoryIsDigit) {
        return ImportAppResult.fail(appInstance + "??maxmemory????");
      }
    }
  }
  return ImportAppResult.success();
}

/** 
 * ???????
 * @param hostId ??id
 * @param ip     ip
 */
@Override public void monitorMachineStats(final long hostId,final String ip){
  Assert.isTrue(hostId > 0);
  Assert.hasText(ip);
  MachineStats machineStats=machineStatsDao.getMachineStatsByIp(ip);
  if (machineStats == null) {
    logger.warn("machine stats is null, ip: {}, time: {}",ip,new Date());
    return;
  }
  double cpuUsage=ObjectConvert.percentToDouble(machineStats.getCpuUsage(),0);
  double memoryUsage=ObjectConvert.percentToDouble(machineStats.getMemoryUsageRatio(),0);
  double load=0;
  try {
    load=Double.valueOf(machineStats.getLoad());
  }
 catch (  NumberFormatException e) {
    logger.error(e.getMessage(),e);
  }
  double memoryThreshold=ConstUtils.MEMORY_USAGE_RATIO_THRESHOLD;
  StringBuilder alertContent=new StringBuilder();
  if (cpuUsage > ConstUtils.CPU_USAGE_RATIO_THRESHOLD) {
    logger.warn("cpuUsageRatio is above security line. ip: {}, cpuUsage: {}%",ip,cpuUsage);
    alertContent.append("ip:").append(ip).append(",cpuUse:").append(cpuUsage);
  }
  if (memoryUsage > memoryThreshold) {
    logger.warn("memoryUsageRatio is above security line, ip: {}, memoryUsage: {}%",ip,memoryUsage);
    alertContent.append("ip:").append(ip).append(",memUse:").append(memoryUsage);
  }
  if (load > ConstUtils.LOAD_THRESHOLD) {
    logger.warn("load is above security line, ip: {}, load: {}%",ip,load);
    alertContent.append("ip:").append(ip).append(",load:").append(load);
  }
  if (StringUtils.isNotBlank(alertContent.toString())) {
    String title="cachecloud????:";
    emailComponent.sendMailToAdmin(title,alertContent.toString());
    mobileAlertComponent.sendPhoneToAdmin(title + alertContent.toString());
  }
}

/** 
 * ?????trigger
 * @param jobGroup  trigger???job???redis/machine/machineMonitor
 * @param ip
 * @param port
 * @return
 */
@Override public boolean addTrigger(String jobGroup,String ip,int port){
  Assert.hasText(jobGroup,"jobGroup is invalid: " + jobGroup);
  Assert.hasText(ip,"ip is invalid, ip: " + ip);
  Assert.isTrue(port > 0,"port is invalid, port: " + port);
  boolean opResult=false;
  if (jobGroup.equals(ConstUtils.REDIS_JOB_GROUP)) {
    InstanceInfo instanceInfo=instanceDao.getInstByIpAndPort(ip,port);
    opResult=redisCenter.deployRedisCollection(instanceInfo.getAppId(),ip,port);
  }
 else   if (jobGroup.equals(ConstUtils.REDIS_SLOWLOG_JOB_GROUP)) {
    InstanceInfo instanceInfo=instanceDao.getInstByIpAndPort(ip,port);
    opResult=redisCenter.deployRedisSlowLogCollection(instanceInfo.getAppId(),ip,port);
  }
 else   if (jobGroup.equals(ConstUtils.MACHINE_JOB_GROUP)) {
    MachineInfo machineInfo=machineDao.getMachineInfoByIp(ip);
    opResult=machineCenter.deployMachineCollection(machineInfo.getId(),ip);
  }
 else   if (jobGroup.equals(ConstUtils.MACHINE_MONITOR_JOB_GROUP)) {
    MachineInfo machineInfo=machineDao.getMachineInfoByIp(ip);
    opResult=machineCenter.deployMachineMonitor(machineInfo.getId(),ip);
  }
 else   if (jobGroup.equals(ConstUtils.SERVER_TRIGGER_GROUP)) {
    MachineInfo machineInfo=machineDao.getMachineInfoByIp(ip);
    opResult=machineCenter.deployServerCollection(machineInfo.getId(),ip);
  }
  return opResult;
}

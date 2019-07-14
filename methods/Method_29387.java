private InstanceInfo saveInstance(long appId,String host,int port,int maxMemory){
  InstanceInfo instanceInfo=new InstanceInfo();
  instanceInfo.setAppId(appId);
  MachineInfo machineInfo=machineCenter.getMachineInfoByIp(host);
  instanceInfo.setHostId(machineInfo.getId());
  instanceInfo.setConn(0);
  instanceInfo.setMem(maxMemory);
  instanceInfo.setStatus(InstanceStatusEnum.GOOD_STATUS.getStatus());
  instanceInfo.setPort(port);
  instanceInfo.setType(ConstUtils.CACHE_TYPE_REDIS_CLUSTER);
  instanceInfo.setCmd("");
  instanceInfo.setIp(host);
  instanceDao.saveInstance(instanceInfo);
  return instanceInfo;
}

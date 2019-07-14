private void saveFault(InstanceInfo info,boolean isRun){
  InstanceFault instanceFault=new InstanceFault();
  instanceFault.setAppId((int)info.getAppId());
  instanceFault.setInstId(info.getId());
  instanceFault.setIp(info.getIp());
  instanceFault.setPort(info.getPort());
  instanceFault.setType(info.getType());
  instanceFault.setCreateTime(new Date());
  if (isRun) {
    instanceFault.setReason("????");
  }
 else {
    instanceFault.setReason("????");
  }
  instanceFaultDao.insert(instanceFault);
}

/** 
 * ?source??startSlot?endSlot???target
 */
public boolean migrateSlot(InstanceReshardProcess instanceReshardProcess){
  long appId=instanceReshardProcess.getAppId();
  int migratingSlot=instanceReshardProcess.getMigratingSlot();
  int endSlot=instanceReshardProcess.getEndSlot();
  int isPipeline=instanceReshardProcess.getIsPipeline();
  InstanceInfo sourceInstanceInfo=instanceReshardProcess.getSourceInstanceInfo();
  InstanceInfo targetInstanceInfo=instanceReshardProcess.getTargetInstanceInfo();
  long startTime=System.currentTimeMillis();
  Jedis sourceJedis=redisCenter.getJedis(appId,sourceInstanceInfo.getIp(),sourceInstanceInfo.getPort(),defaultTimeout,defaultTimeout);
  Jedis targetJedis=redisCenter.getJedis(appId,targetInstanceInfo.getIp(),targetInstanceInfo.getPort(),defaultTimeout,defaultTimeout);
  boolean hasError=false;
  for (int slot=migratingSlot; slot <= endSlot; slot++) {
    long slotStartTime=System.currentTimeMillis();
    try {
      instanceReshardProcessDao.updateMigratingSlot(instanceReshardProcess.getId(),slot);
      int num=migrateSlotData(appId,sourceJedis,targetJedis,slot,isPipeline);
      instanceReshardProcessDao.increaseFinishSlotNum(instanceReshardProcess.getId());
      logger.warn("clusterReshard:{}->{}, slot={}, keys={}, costTime={} ms",sourceInstanceInfo.getHostPort(),targetInstanceInfo.getHostPort(),slot,num,(System.currentTimeMillis() - slotStartTime));
    }
 catch (    Exception e) {
      logger.error(e.getMessage(),e);
      hasError=true;
      break;
    }
  }
  long endTime=System.currentTimeMillis();
  logger.warn("clusterReshard:{}->{}, slot:{}->{}, costTime={} ms",sourceInstanceInfo.getHostPort(),targetInstanceInfo.getHostPort(),migratingSlot,endSlot,(endTime - startTime));
  if (hasError) {
    instanceReshardProcessDao.updateStatus(instanceReshardProcess.getId(),ReshardStatusEnum.ERROR.getValue());
    return false;
  }
 else {
    instanceReshardProcessDao.updateStatus(instanceReshardProcess.getId(),ReshardStatusEnum.FINISH.getValue());
    instanceReshardProcessDao.updateEndTime(instanceReshardProcess.getId(),new Date());
    return true;
  }
}

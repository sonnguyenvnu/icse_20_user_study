/** 
 * ????
 * @param appId
 * @param appAuditId
 * @param sourceInstanceInfo
 * @param targetInstanceInfo
 * @param startSlot
 * @param endSlot
 * @return
 */
private InstanceReshardProcess saveInstanceReshardProcess(long appId,long appAuditId,InstanceInfo sourceInstanceInfo,InstanceInfo targetInstanceInfo,int startSlot,int endSlot,PipelineEnum pipelineEnum){
  Date now=new Date();
  InstanceReshardProcess instanceReshardProcess=new InstanceReshardProcess();
  instanceReshardProcess.setAppId(appId);
  instanceReshardProcess.setAuditId(appAuditId);
  instanceReshardProcess.setFinishSlotNum(0);
  instanceReshardProcess.setIsPipeline(pipelineEnum.getValue());
  instanceReshardProcess.setSourceInstanceId(sourceInstanceInfo.getId());
  instanceReshardProcess.setTargetInstanceId(targetInstanceInfo.getId());
  instanceReshardProcess.setMigratingSlot(startSlot);
  instanceReshardProcess.setStartSlot(startSlot);
  instanceReshardProcess.setEndSlot(endSlot);
  instanceReshardProcess.setStatus(ReshardStatusEnum.RUNNING.getValue());
  instanceReshardProcess.setStartTime(now);
  instanceReshardProcess.setEndTime(now);
  instanceReshardProcess.setCreateTime(now);
  instanceReshardProcess.setUpdateTime(now);
  instanceReshardProcessDao.save(instanceReshardProcess);
  return instanceReshardProcess;
}

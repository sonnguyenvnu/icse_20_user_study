private void startMigrateSlot(final InstanceReshardProcess instanceReshardProcess){
  final long appId=instanceReshardProcess.getAppId();
  final long appAuditId=instanceReshardProcess.getAuditId();
  final InstanceInfo targetInstanceInfo=instanceReshardProcess.getTargetInstanceInfo();
  processThreadPool.execute(new Runnable(){
    @Override public void run(){
      Set<HostAndPort> clusterHosts=getEffectiveInstanceList(appId);
      RedisClusterReshard clusterReshard=new RedisClusterReshard(clusterHosts,redisCenter,instanceReshardProcessDao);
      boolean joinCluster=clusterReshard.migrateSlot(instanceReshardProcess);
      if (joinCluster) {
        appAuditDao.updateAppAudit(appAuditId,AppCheckEnum.APP_ALLOCATE_RESOURCE.value());
        if (targetInstanceInfo != null && targetInstanceInfo.getStatus() != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
          targetInstanceInfo.setStatus(InstanceStatusEnum.GOOD_STATUS.getStatus());
          instanceDao.update(targetInstanceInfo);
        }
      }
    }
  }
);
}

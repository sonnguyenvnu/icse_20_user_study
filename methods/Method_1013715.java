@Override public void refresh(){
  MigrationCluster holder=getHolder();
  ClusterStepResult clusterStepResult=holder.stepStatus(ShardMigrationStep.CHECK);
  if (clusterStepResult.isStepFinish()) {
    if (clusterStepResult.isStepSuccess()) {
      logger.debug("[refresh][check success]{}",this);
      updateAndProcess(nextAfterSuccess());
    }
 else {
      logger.debug("[refresh][check fail]{}",this);
      updateAndStop(nextAfterFail());
    }
  }
}

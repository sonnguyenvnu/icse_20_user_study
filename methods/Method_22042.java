@Override public void stateChanged(final CuratorFramework client,final ConnectionState newState){
  if (JobRegistry.getInstance().isShutdown(jobName)) {
    return;
  }
  JobScheduleController jobScheduleController=JobRegistry.getInstance().getJobScheduleController(jobName);
  if (ConnectionState.SUSPENDED == newState || ConnectionState.LOST == newState) {
    jobScheduleController.pauseJob();
  }
 else   if (ConnectionState.RECONNECTED == newState) {
    serverService.persistOnline(serverService.isEnableServer(JobRegistry.getInstance().getJobInstance(jobName).getIp()));
    instanceService.persistOnline();
    executionService.clearRunningInfo(shardingService.getLocalShardingItems());
    jobScheduleController.resumeJob();
  }
}

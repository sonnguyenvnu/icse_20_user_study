/** 
 * ????????????????.
 * @return ??????????????
 */
public List<Integer> getLocalShardingItems(){
  if (JobRegistry.getInstance().isShutdown(jobName) || !serverService.isAvailableServer(JobRegistry.getInstance().getJobInstance(jobName).getIp())) {
    return Collections.emptyList();
  }
  return getShardingItems(JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId());
}

private boolean needFailover(){
  return jobNodeStorage.isJobNodeExisted(FailoverNode.ITEMS_ROOT) && !jobNodeStorage.getJobNodeChildrenKeys(FailoverNode.ITEMS_ROOT).isEmpty() && !JobRegistry.getInstance().isJobRunning(jobName);
}

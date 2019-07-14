private void disableOrEnableJobs(final String jobName,final String item,final boolean disabled){
  JobNodePath jobNodePath=new JobNodePath(jobName);
  String shardingDisabledNodePath=jobNodePath.getShardingNodePath(item,"disabled");
  if (disabled) {
    regCenter.persist(shardingDisabledNodePath,"");
  }
 else {
    regCenter.remove(shardingDisabledNodePath);
  }
}

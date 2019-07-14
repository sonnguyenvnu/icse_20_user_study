private boolean isHasShardingFlag(final JobNodePath jobNodePath,final List<String> instances){
  Set<String> shardingInstances=new HashSet<>();
  for (  String each : regCenter.getChildrenKeys(jobNodePath.getShardingNodePath())) {
    String instanceId=regCenter.get(jobNodePath.getShardingNodePath(each,"instance"));
    if (null != instanceId && !instanceId.isEmpty()) {
      shardingInstances.add(instanceId);
    }
  }
  return !instances.containsAll(shardingInstances) || shardingInstances.isEmpty();
}

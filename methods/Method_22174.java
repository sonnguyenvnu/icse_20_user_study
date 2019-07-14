@Override public Collection<ShardingInfo> getShardingInfo(final String jobName){
  String shardingRootPath=new JobNodePath(jobName).getShardingNodePath();
  List<String> items=regCenter.getChildrenKeys(shardingRootPath);
  List<ShardingInfo> result=new ArrayList<>(items.size());
  for (  String each : items) {
    result.add(getShardingInfo(jobName,each));
  }
  Collections.sort(result);
  return result;
}

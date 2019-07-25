public long count(String index,String type,String shardId,QueryBuilder query){
  return clusterInfo.getMajorVersion().onOrAfter(EsMajorVersion.V_5_X) ? countInES5X(index,type,shardId,query) : countBeforeES5X(index + "/" + type,shardId,query);
}

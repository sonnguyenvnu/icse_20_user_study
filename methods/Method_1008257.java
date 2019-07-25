protected IndexShardRoutingTable shards(ClusterState clusterState,String index,String id,String routing){
  int shardId=generateShardId(indexMetaData(clusterState,index),id,routing);
  return clusterState.getRoutingTable().shardRoutingTable(index,shardId);
}

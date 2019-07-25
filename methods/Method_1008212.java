/** 
 * @return all shard ids the request should run on
 */
protected List<ShardId> shards(Request request,ClusterState clusterState){
  List<ShardId> shardIds=new ArrayList<>();
  String[] concreteIndices=indexNameExpressionResolver.concreteIndexNames(clusterState,request);
  for (  String index : concreteIndices) {
    IndexMetaData indexMetaData=clusterState.metaData().getIndices().get(index);
    if (indexMetaData != null) {
      for (      IntObjectCursor<IndexShardRoutingTable> shardRouting : clusterState.getRoutingTable().indicesRouting().get(index).getShards()) {
        shardIds.add(shardRouting.value.shardId());
      }
    }
  }
  return shardIds;
}
